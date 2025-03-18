package com.rauxasoft.liga_futbol.presentation.triggers;

import java.util.ArrayList;
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

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
@Hidden
public class TriggerController {
	
	private Thread thread;
	
	private final TiempoDeJuego tiempoDeJuego;
	private final PartidoServices partidoServices;
	private final PartidoRepository partidoRepository;
	private final LanceSimuladoRepository lanceSimuladoRepository;

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
			
			Optional<Partido> optional11 = partidoRepository.findById(11L);
			Optional<Partido> optional12 = partidoRepository.findById(12L);
			
			Partido partido11 = optional11.get();
			Partido partido12 = optional12.get();
			
			List<Lance> lancesPartido11 = new ArrayList<>();
			List<Lance> lancesPartido12 = new ArrayList<>();
			
			int partido11_golesLocal = 0;
			int partido11_golesVisitante = 0;
			
			int partido12_golesLocal = 0;
			int partido12_golesVisitante = 0;
						
			while(tiempoDeJuego.getMinuto() <= 95) {
				
				try {
					
					Thread.sleep(intervalo);
					
					List<LanceSimulado> lancesSimulados = lanceSimuladoRepository.findByMinuto(tiempoDeJuego.getMinuto());
					
					System.out.println("Minuto " + tiempoDeJuego.getMinuto() + ": " + lancesSimulados);
					
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
							case "GOL_VISITANTE": break;
							case "GOL_VISITANTE_ANULADO": break;
							case "GOL_LOCAL": break;
							case "GOL_LOCAL_ANULADO": break;
						}
						
						// INICIO_DEL_PARTIDO   ---> EN_JUEGO_PRIMERA_PARTE
						// FIN_PRIMERA_PARTE    ---> DESCANSO
						// INICIO_SEGUNDA_PARTE ---> EN_JUEGO_SEGUNDA_PARTE
						// FINAL_DEL_PARTIDO    ---> FINALIZADO
					
						if (idPartido == 11L) {
							
							if (tipoLance.getNombre().equals("INICIO_DEL_PARTIDO")) {
								partido11.setGolesLocal(partido11_golesLocal);
								partido11.setGolesVisitante(partido11_golesVisitante);
							}
							
							if (tipoLance.getNombre().equals("GOL_LOCAL")) {
								partido11.setGolesLocal(++partido11_golesLocal);
							}
							
							if (tipoLance.getNombre().equals("GOL_LOCA_ANULADO")) {
								partido11.setGolesLocal(--partido11_golesLocal);
							}
							
							if (tipoLance.getNombre().equals("GOL_VISITANTE")) {
								partido11.setGolesLocal(++partido11_golesVisitante);
							}
							
							if (tipoLance.getNombre().equals("GOL_LOCA_ANULADO")) {
								partido11.setGolesLocal(--partido11_golesVisitante);
							}
							
							lancesPartido11.add(lance);
							
							if(estadoPartido != null) {
								partido11.setEstado(estadoPartido);
							}
							
						} else {
							
							if (tipoLance.getNombre().equals("INICIO_DEL_PARTIDO")) {
								partido12.setGolesLocal(partido12_golesLocal);
								partido12.setGolesVisitante(partido12_golesVisitante);
							}
							
							if (tipoLance.getNombre().equals("GOL_LOCAL")) {
								partido12.setGolesLocal(++partido12_golesLocal);
							}
							
							if (tipoLance.getNombre().equals("GOL_LOCA_ANULADO")) {
								partido12.setGolesLocal(--partido12_golesLocal);
							}
							
							if (tipoLance.getNombre().equals("GOL_VISITANTE")) {
								partido12.setGolesLocal(++partido12_golesVisitante);
							}
							
							if (tipoLance.getNombre().equals("GOL_LOCA_ANULADO")) {
								partido12.setGolesLocal(--partido12_golesVisitante);
							}
							
							lancesPartido12.add(lance);
							
							if(estadoPartido != null) {
								partido12.setEstado(estadoPartido);
							}
					
						}
						
					}
					
					System.out.println("Tiempo de juego: " + tiempoDeJuego.getMinuto());
					
					tiempoDeJuego.incrementar();
					partido11.setLances(lancesPartido11);
					partido12.setLances(lancesPartido12);
					partidoRepository.save(partido11);
					partidoRepository.save(partido12);
					
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
