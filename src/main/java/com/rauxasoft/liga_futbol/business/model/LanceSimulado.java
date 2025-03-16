package com.rauxasoft.liga_futbol.business.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="SIMULADOR_LANCES")
public class LanceSimulado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(name="INSTANTE_TIEMPO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date instanteTiempo;
	
	private Integer minuto;
	private Long idPartido;
	private String idLance;
	private String comentario;
	
	public LanceSimulado() {
		
	}

	public LanceSimulado(Date instanteTiempo, Integer minuto, String idLance, String comentario) {
		this.instanteTiempo = instanteTiempo;
		this.minuto = minuto;
		this.idLance = idLance;
		this.comentario = comentario;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstanteTiempo() {
		return instanteTiempo;
	}

	public void setInstanteTiempo(Date instanteTiempo) {
		this.instanteTiempo = instanteTiempo;
	}

	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}
	
	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}

	public String getIdLance() {
		return idLance;
	}

	public void setIdLance(String idLance) {
		this.idLance = idLance;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

}
