package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.Equipo;
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
	public Map<Long, EstadisticasEquipo> getClasificacion() {
		
		// 0.- MONTAMOS LA TABLA DE EQUIPOS CON SUS ESTADISTICAS
		
		List<Equipo> equipos = equipoServices.getAll();
		
		Map<Long, EstadisticasEquipo> tablaEquipos = new HashMap<>();
		
		for(Equipo equipo: equipos) {
			tablaEquipos.put(equipo.getId(), new EstadisticasEquipo(equipo.getId(), equipo.getNombre()));
		}
		
		// 1.- CALCULAMOS NÚMERO DE PARTIDOS JUGADOS
		
		List<Partido> partidosComputables = partidoServices.getPartidosNoPendientes();
		
		for(Partido partido: partidosComputables) {
			Long idEquipoLocal = partido.getLocal().getId();
			Long idEquipoVisitante = partido.getVisitante().getId();
			tablaEquipos.get(idEquipoLocal).incrementarLocalPartidosJugados();
			tablaEquipos.get(idEquipoVisitante).incrementarVisitantePartidosJugados();
		}
		
		// 2.- CALCULAMOS NÚMERO DE GOLES A FAVOR Y EN CONTRA
		
		List<Object[]> datos = partidoServices.getEstadisticasPartidos();
		
		Iterator<Object[]> iterator = datos.iterator();
		
		int totalGolesLocal = 0;
		int totalGolesVisitante = 0;
		Long idPartidoActual = (Long) datos.get(0)[0];
		Long idEquipoLocal = null;
		Long idEquipoVisitante = null;
		String lance;
		
		while(iterator.hasNext()) {
			
			Object[] fila = iterator.next();
			
			Long idPartido = (Long) fila[0];
		
			// Si hay rotura de control
			
			if(idPartido != idPartidoActual) {
				generarEstadistica(tablaEquipos, idEquipoLocal, totalGolesLocal, idEquipoVisitante, totalGolesVisitante);
				totalGolesLocal = 0;
				totalGolesVisitante = 0;
				idPartidoActual = idPartido;
			}
			
			idEquipoLocal = (Long) fila[1];
			idEquipoVisitante = (Long) fila[2];
			lance = (String) fila[3];
			
			System.out.println(idPartido + " " + idEquipoLocal + " " + idEquipoVisitante + " " + lance);
			
			// Acumulamos info (siempre se debe hacer)
			
			switch(lance) {
			
				case "GOL_LOCAL": {
					tablaEquipos.get(idEquipoLocal).addLocalGolesFavor(1);
					tablaEquipos.get(idEquipoVisitante).addVisitanteGolesContra(1);
					++totalGolesLocal;
					break;
				}
				
				case "GOL_VISITANTE": {
					tablaEquipos.get(idEquipoLocal).addLocalGolesContra(1);
					tablaEquipos.get(idEquipoVisitante).addVisitanteGolesFavor(1);
					++totalGolesVisitante;
					break;
				}
				
				case "GOL_LOCAL_ANULADO": {
					tablaEquipos.get(idEquipoLocal).addLocalGolesFavor(-1);
					tablaEquipos.get(idEquipoVisitante).addVisitanteGolesContra(-1);
					--totalGolesLocal;
					break;
				}
				
				case "GOL_VISITANTE_ANULADO":{
					tablaEquipos.get(idEquipoLocal).addLocalGolesContra(-1);
					tablaEquipos.get(idEquipoVisitante).addVisitanteGolesFavor(-1);
					--totalGolesVisitante;
					break;
				}
			}
				
		}
		
		generarEstadistica(tablaEquipos, idEquipoLocal, totalGolesLocal, idEquipoVisitante, totalGolesVisitante);
		
		// El problema será establecer el orden de la clasificación! Esto viene ahora...
	
		Map<Long, EstadisticasEquipo> tabla = new HashMap<>();
		
		tablaEquipos.forEach((k, v) -> {
			tabla.put(k, v);
		});
		
		return tabla;
	}
	
	// ******************************************************************************
	//
	// 							  Private Methods
	//
	// ******************************************************************************
	
	private void generarEstadistica(Map<Long, EstadisticasEquipo> tablaEquipos, Long idEquipoLocal, int totalGolesLocal, Long idEquipoVisitante, int totalGolesVisitante) {
		
		if(totalGolesLocal == totalGolesVisitante) {
			tablaEquipos.get(idEquipoLocal).incrementarLocalPartidosEmpatados();
			tablaEquipos.get(idEquipoVisitante).incrementarVisitantePartidosEmpatados();
		}
		
		if(totalGolesLocal > totalGolesVisitante) {
			tablaEquipos.get(idEquipoLocal).incrementarLocalPartidosGanados();
			tablaEquipos.get(idEquipoVisitante).incrementarVisitantePartidosPerdidos();
		}
		
		if(totalGolesLocal < totalGolesVisitante) {
			tablaEquipos.get(idEquipoLocal).incrementarLocalPartidosPerdidos();
			tablaEquipos.get(idEquipoVisitante).incrementarVisitantePartidosGanados();
		}
		
		System.out.println("RESUMIMOS");
	}

}
