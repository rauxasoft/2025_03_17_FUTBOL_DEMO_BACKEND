package com.rauxasoft.liga_futbol.business.model.dtos;

import java.io.Serializable;
import java.util.Objects;

public class EstadisticasEquipo implements Serializable, Comparable<EstadisticasEquipo> {
	private static final long serialVersionUID = 1L;
	
	private Long idEquipo;
	private String nombreEquipo;
	
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
	
	public EstadisticasEquipo(Long idEquipo, String nombreEquipo) {
		this.idEquipo = idEquipo;
		this.nombreEquipo = nombreEquipo;
	}
	
	public Long getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Long idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	// **************** GETTERS/SETTERS LOCAL *******************

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

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
		return Objects.hash(idEquipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadisticasEquipo other = (EstadisticasEquipo) obj;
		return Objects.equals(idEquipo, other.idEquipo);
	}

	@Override
	public int compareTo(EstadisticasEquipo o) {
		
		if(this.getTotalPuntos() != o.getTotalPuntos()) {
			return o.getTotalPuntos() - this.getTotalPuntos();
		}
		
		return 0;
	}

}
