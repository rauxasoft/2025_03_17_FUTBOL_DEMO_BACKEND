package com.rauxasoft.liga_futbol.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="PARTIDOS")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name="ID_JORNADA")
	private Integer numeroJornada;
	
	@ManyToOne
	@JoinColumn(name="ID_ARBITRO")
	private Arbitro arbitro;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="ID_EQUIPO_LOCAL")
	private Equipo local;
	
	@ManyToOne
	@JoinColumn(name="ID_EQUIPO_VISITANTE")
	private Equipo visitante;
	
	@Enumerated(EnumType.STRING)
	private EstadoPartido estado;
	
	@Column(name="GOLES_LOCAL")
	private Integer golesLocal;
	
	@Column(name="GOLES_VISITANTE")
	private Integer golesVisitante;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "LANCES", joinColumns = @JoinColumn(name="ID_PARTIDO"))
	@OrderColumn(name="INDEX")
	private List<Lance> lances;
	
	public Partido() {
		
	}
	
	public Partido(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroJornada() {
		return numeroJornada;
	}

	public void setNumeroJornada(Integer numeroJornada) {
		this.numeroJornada = numeroJornada;
	}
	
	public Arbitro getArbitro() {
		return arbitro;
	}

	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	
	public EstadoPartido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPartido estado) {
		this.estado = estado;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}
	
	public void addLance(Lance lance) {
		this.lances.add(lance);
	}
	
	public Integer getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}

	public Integer getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		return Objects.equals(id, other.id);
	}

}
