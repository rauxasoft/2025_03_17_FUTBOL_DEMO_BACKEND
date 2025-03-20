package com.rauxasoft.liga_futbol.business.model.dtos;

import java.io.Serializable;
import java.util.Objects;

import com.rauxasoft.liga_futbol.business.model.Equipo;

public class EstadisticasEquipo_BK implements Serializable, Comparable<EstadisticasEquipo_BK> {
	private static final long serialVersionUID = 1L;
	
	private int clasificacion;
	private Equipo equipo;
	
	private int localPartidosJugados;
	private int localPartidosGanados;
	private int localPartidosEmpatados;
	private int localPartidosPerdidos;
	private int localGolesFavor;
	private int localGolesContra;
	
	private int visitantePartidosJugados;
	private int visitantePartidosGanados;
	private int visitantePartidosEmpatados;
	private int visitantePartidosPerdidos;
	private int visitanteGolesFavor;
	private int visitanteGolesContra;
	
	public EstadisticasEquipo_BK(int clasificacion, Equipo equipo) {
		this.clasificacion = clasificacion;
		this.equipo = equipo;
	}
	
	public int getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(int clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	// **************** GETTERS/SETTERS LOCAL *******************

	public int getLocalPartidosJugados() {
		return localPartidosJugados;
	}
	
	public int getLocalPartidosGanados() {
		return localPartidosGanados;
	}
	
	public int getLocalPartidosEmpatados() {
		return localPartidosEmpatados;
	}
	
	public int getLocalPartidosPerdidos() {
		return localPartidosPerdidos;
	}
	
	public int getLocalGolesFavor() {
		return localGolesFavor;
	}
	
	public int getLocalGolesContra() {
		return localGolesContra;
	}


	public void incrementarLocalPartidosJugados() {
		this.localPartidosJugados++;
	}

	public void incrementarLocalPartidosGanados() {
		this.localPartidosGanados++;
	}

	public void incrementarLocalPartidosEmpatados() {
		this.localPartidosEmpatados++;
	}

	public void incrementarLocalPartidosPerdidos() {
		this.localPartidosPerdidos++;
	}

	public void addLocalGolesFavor(int localGolesFavor) {
		this.localGolesFavor += localGolesFavor;
	}

	public void addLocalGolesContra(int localGolesContra) {
		this.localGolesContra += localGolesContra;
	}
	
	// **************** GETTERS/SETTERS VISITANTE *******************

	public int getVisitantePartidosJugados() {
		return visitantePartidosJugados;
	}
	
	public int getVisitantePartidosGanados() {
		return visitantePartidosGanados;
	}

	public int getVisitantePartidosEmpatados() {
		return visitantePartidosEmpatados;
	}
	
	public int getVisitantePartidosPerdidos() {
		return visitantePartidosPerdidos;
	}
	
	public int getVisitanteGolesFavor() {
		return visitanteGolesFavor;
	}
	
	public int getVisitanteGolesContra() {
		return visitanteGolesContra;
	}
	
	public void incrementarVisitantePartidosJugados() {
		this.visitantePartidosJugados++;
	}
	
	public void incrementarVisitantePartidosGanados() {
		this.visitantePartidosGanados++;
	}

	public void incrementarVisitantePartidosEmpatados() {
		this.visitantePartidosEmpatados++;
	}

	public void incrementarVisitantePartidosPerdidos() {
		this.visitantePartidosPerdidos++;
	}
	
	public void addVisitanteGolesFavor(int visitanteGolesFavor) {
		this.visitanteGolesFavor += visitanteGolesFavor;
	}

	public void addVisitanteGolesContra(int visitanteGolesContra) {
		this.visitanteGolesContra += visitanteGolesContra;
	}

	// **************** GETTERS TOTAL *******************
		
	public int getTotalPartidosJugados() {
		return localPartidosJugados + visitantePartidosJugados;
	}

	public int getTotalPartidosGanados() {
		return localPartidosGanados + visitantePartidosGanados;
	}

	public int getTotalPartidosEmpatados() {
		return localPartidosEmpatados + visitantePartidosEmpatados;
	}
	
	public int getTotalPartidosPerdidos() {
		return localPartidosPerdidos + visitantePartidosPerdidos;
	}

	public int getTotalGolesFavor() {
		return localGolesFavor + visitanteGolesFavor;
	}
		
	public int getTotalGolesContra() {
		return localGolesContra + visitanteGolesContra;
	}
	
	public int getLocalPuntos() {
		return (localPartidosGanados * 3) + localPartidosEmpatados;
	}
	
	public int getVisitantePuntos() {
		return (visitantePartidosGanados * 3) + visitantePartidosEmpatados;
	}
	
	public int getTotalPuntos() {
		return getLocalPuntos() + getVisitantePuntos();
	}

	@Override
	public int hashCode() {
		return Objects.hash(clasificacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticasEquipo_BK other = (EstadisticasEquipo_BK) obj;
		return clasificacion == other.clasificacion;
	}

	@Override
	public int compareTo(EstadisticasEquipo_BK o) {
		
		if(this.getTotalPuntos() != o.getTotalPuntos()) {
			return o.getTotalPuntos() - this.getTotalPuntos();
		}
		
		return 0;
	}

}
