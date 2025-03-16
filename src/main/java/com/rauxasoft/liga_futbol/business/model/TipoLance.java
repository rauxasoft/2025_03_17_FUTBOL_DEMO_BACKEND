package com.rauxasoft.liga_futbol.business.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TIPOS_LANCE")
public class TipoLance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private String nombre;
	
	public TipoLance() {
		
	}
	
	public TipoLance(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoLance other = (TipoLance) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "TipoLance [nombre=" + nombre + "]";
	}
	
}
