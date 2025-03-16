package com.rauxasoft.liga_futbol.business.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="EQUIPOS")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="EQUIPOS_SEQ")
	@SequenceGenerator(name="EQUIPOS_SEQ",sequenceName="EQUIPOS_SEQ", allocationSize=50)
	private Long id;
	
	private String nombre;
	private String ciudad;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FUNDACION")
	private Date fechaFundacion;
	
	public Equipo() {
		
	}
	
	public Equipo(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Date getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(Date fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
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
		Equipo other = (Equipo) obj;
		return Objects.equals(id, other.id);
	}
	
}
