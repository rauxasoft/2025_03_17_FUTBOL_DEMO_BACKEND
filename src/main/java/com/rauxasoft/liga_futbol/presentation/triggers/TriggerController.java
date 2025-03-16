package com.rauxasoft.liga_futbol.presentation.triggers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.EstadoPartido;
import com.rauxasoft.liga_futbol.business.model.Lance;
import com.rauxasoft.liga_futbol.business.model.LanceSimulado;
import com.rauxasoft.liga_futbol.business.model.Partido;
import com.rauxasoft.liga_futbol.business.model.TipoLance;
import com.rauxasoft.liga_futbol.business.services.PartidoServices;
import com.rauxasoft.liga_futbol.integration.repositories.LanceSimuladoRepository;
import com.rauxasoft.liga_futbol.integration.repositories.PartidoRepository;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
public class TriggerController {
	
	private final TiempoDeJuego tiempoDeJuego;
	private final PartidoServices partidoServices;
	private final PartidoRepository partidoRepository;
	private final LanceSimuladoRepository lanceSimuladoRepository;
	private Thread thread;
	
	public TriggerController(TiempoDeJuego tiempoDeJuego, 
							 PartidoServices partidoServices, 
							 PartidoRepository partidoRepository,
							 LanceSimuladoRepository lanceSimuladoRepository) {
		this.tiempoDeJuego = tiempoDeJuego;
		this.partidoServices = partidoServices;
		this.lanceSimuladoRepository = lanceSimuladoRepository;
		this.partidoRepository = partidoRepository;
	}

	@GetMapping("/trigger/reset")
	@Transactional
	public String reset() {
		
		tiempoDeJuego.reset();
		
		Optional<Partido> optional11 = partidoServices.read(11L);
		Optional<Partido> optional12 = partidoServices.read(12L);
		
		Partido partido11 = optional11.get();
		Partido partido12 = optional12.get();
		
		partido11.setGolesLocal(null);
		partido11.setGolesVisitante(null);
		partido11.setEstado(EstadoPartido.PENDIENTE);
		partido11.getLances().clear();
		
		partido12.setGolesLocal(null);
		partido12.setGolesVisitante(null);
		partido12.setEstado(EstadoPartido.PENDIENTE);
		partido12.getLances().clear();
		
		if (this.thread != null) {
			
			
			System.out.println("Finalizando Thread de la última jornada");
			this.thread.interrupt();
		}
		
		return "Liga de futbol reseteada al estado inicial antes de la última jornada";
	}
	
	@GetMapping("/trigger/start")
	@Transactional
	public String start(@RequestParam(required = false, defaultValue = "1000") Long intervalo) {
		
		tiempoDeJuego.reset();
	
		Runnable runnable = () -> { 
			
			Optional<Partido> optional11 = partidoServices.read(11L);
			Optional<Partido> optional12 = partidoServices.read(12L);
			
			Partido partido11 = optional11.get();
			Partido partido12 = optional12.get();
			
			while(tiempoDeJuego.getMinuto() <= 95) {
				
				try {
					
					Thread.sleep(intervalo);
					
					List<LanceSimulado> lancesSimulados = lanceSimuladoRepository.findByMinuto(tiempoDeJuego.getMinuto());
					
					for(LanceSimulado lanceSimulado: lancesSimulados) {
						Long idPartido = lanceSimulado.getIdPartido();
						TipoLance tipoLance = new TipoLance(lanceSimulado.getIdLance());
						
						Lance lance = new Lance();
						lance.setTipoLance(tipoLance);
						
						lance.setMinuto(lanceSimulado.getMinuto());
						lance.setComentario(lanceSimulado.getComentario());
						lance.setInstanteTiempo(lanceSimulado.getInstanteTiempo());
						
						EstadoPartido estadoPartido = null;
						
						switch(tipoLance.getNombre()) {
							case "INICIO_DEL_PARTIDO": estadoPartido = EstadoPartido.EN_JUEGO_PRIMERA_PARTE; break;
							case "FIN_PRIMERA_PARTE": estadoPartido = EstadoPartido.DESCANSO; break;
							case "INICIO_SEGUNDA_PARTE": estadoPartido = EstadoPartido.EN_JUEGO_SEGUNDA_PARTE; break;
							case "FINAL_DEL_PARTIDO": estadoPartido = EstadoPartido.FINALIZADO; break;
						}
						
						// INICIO_DEL_PARTIDO   ---> EN_JUEGO_PRIMERA_PARTE
						// FIN_PRIMERA_PARTE    ---> DESCANSO
						// INICIO_SEGUNDA_PARTE ---> EN_JUEGO_SEGUNDA_PARTE
						// FINAL_DEL_PARTIDO    ---> FINALIZADO
					
						if (idPartido == 11L) {
							
							partido11.addLance(lance);
							if(estadoPartido != null) {
								partido11.setEstado(estadoPartido);
							}
							partidoRepository.save(partido11);
							partidoRepository.flush();
							
						} else {
							
							partido12.addLance(lance);
							if(estadoPartido != null) {
								partido12.setEstado(estadoPartido);
							}
							partidoRepository.save(partido12);
							partidoRepository.flush();
						}
						
					}
					
					System.out.println("Tiempo de juego: " + tiempoDeJuego.getMinuto());
					tiempoDeJuego.incrementar();
					
				} catch (InterruptedException e) {
					
				}
			}
			
			this.thread.interrupt();
		
		};
		
		this.thread = new Thread(runnable);
		this.thread.start();
		
		
		return "inicia última jornada";
	}
	
}
