package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.Equipo;
import com.rauxasoft.liga_futbol.business.model.Lance;
import com.rauxasoft.liga_futbol.business.model.Partido;
import com.rauxasoft.liga_futbol.business.model.dtos.EstadisticasEquipo;
import com.rauxasoft.liga_futbol.business.services.EquipoServices;
import com.rauxasoft.liga_futbol.business.services.EstadisticaServices;
import com.rauxasoft.liga_futbol.business.services.PartidoServices;

@Service
public class EstadisticaServicesImpl implements EstadisticaServices {

	private EquipoServices equipoServices;			
	private PartidoServices partidoServices;
	
	public EstadisticaServicesImpl(EquipoServices equipoServices, PartidoServices partidoServices) {
		this.equipoServices = equipoServices;
		this.partidoServices = partidoServices;
	}
	
	@Override
	public Map<Integer, EstadisticasEquipo> getClasificacion() {
			
		Map<Equipo, EstadisticasEquipo> tablaEquipos = new HashMap<>();
		Map<Integer, EstadisticasEquipo> tabla = new TreeMap<>();
		
		List<Equipo> equipos = equipoServices.getAll();
		
		for(int i = 0; i < equipos.size(); i++) {
			tablaEquipos.put(equipos.get(i), new EstadisticasEquipo(i, equipos.get(i)));
		}
		
		List<Partido> partidos = partidoServices.getAll();
		
		for(Partido partido: partidos) {
			
			Equipo local = partido.getLocal();
			Equipo visitante = partido.getVisitante();
			
			List<Lance> lances = partido.getLances();
			
			boolean iniciado = false; 
			
			if(lances != null) {
				iniciado = lances.stream().map(x -> x.getTipoLance().getNombre()).anyMatch(x -> x.equals("INICIO_DEL_PARTIDO"));
			}
			
			if(iniciado) {
			
				int localGolesFavor = 0;
				int visitanteGolesFavor = 0;
				
				for(Lance lance: lances) {
					
					String nombreLance = lance.getTipoLance().getNombre();
					
					switch(nombreLance) {
						case "GOL_LOCAL": 			  localGolesFavor++;     break;
						case "GOL_LOCAL_ANULADO": 	  localGolesFavor--;     break;
						case "GOL_VISITANTE": 		  visitanteGolesFavor++; break;						
						case "GOL_VISITANTE_ANULADO": visitanteGolesFavor--; break;
						default: break;
					}
	
				}
				
				EstadisticasEquipo estadisticasEquipoLocal = tablaEquipos.get(local);
				EstadisticasEquipo estadisticasEquipoVisitante = tablaEquipos.get(visitante);
				
				estadisticasEquipoLocal.incrementarLocalPartidosJugados();
				estadisticasEquipoLocal.addLocalGolesFavor(localGolesFavor);
				estadisticasEquipoLocal.addLocalGolesContra(visitanteGolesFavor);
				
				estadisticasEquipoVisitante.incrementarVisitantePartidosJugados();
				estadisticasEquipoVisitante.addVisitanteGolesFavor(visitanteGolesFavor);
				estadisticasEquipoVisitante.addVisitanteGolesContra(localGolesFavor);
				
				if (localGolesFavor == visitanteGolesFavor) {
					estadisticasEquipoLocal.incrementarLocalPartidosEmpatados();
					estadisticasEquipoVisitante.incrementarLocalPartidosEmpatados();
				} else {
					if(localGolesFavor > visitanteGolesFavor) {
						estadisticasEquipoLocal.incrementarLocalPartidosGanados();
						estadisticasEquipoVisitante.incrementarVisitantePartidosPerdidos();
					} else {
						estadisticasEquipoLocal.incrementarLocalPartidosPerdidos();
						estadisticasEquipoVisitante.incrementarVisitantePartidosGanados();
					}
				}
			}
				
		}
		
		List<EstadisticasEquipo> estadisticaEquipos = new ArrayList<>( tablaEquipos.values());
		Collections.sort(estadisticaEquipos);
		
		// TODO 
		
		// 1.- Mirar el "cara a cara" entre posibles equipos empatados a puntos (
		// 2.- Si persiste la igualdad se mira el gol average
		
		int i = 0;
		
		for(EstadisticasEquipo estadisticaEquipo: estadisticaEquipos) {
			tabla.put(i, estadisticaEquipo);
			i++;
		}
		
		return tabla;
	}

}
