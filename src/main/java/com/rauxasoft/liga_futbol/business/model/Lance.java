package com.rauxasoft.liga_futbol.business.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class Lance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="INSTANTE_TIEMPO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date instanteTiempo;
	
	private Integer minuto;
	
	@ManyToOne()
	@JoinColumn(name="ID_LANCE")
	private TipoLance tipoLance;
	
	private String comentario;
	
	public Lance() {
		
	}

	public Lance(Date instanteTiempo, Integer minuto, TipoLance tipoLance, String comentario) {
		this.instanteTiempo = instanteTiempo;
		this.minuto = minuto;
		this.tipoLance = tipoLance;
		this.comentario = comentario;
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

	public TipoLance getTipoLance() {
		return tipoLance;
	}

	public void setTipoLance(TipoLance tipoLance) {
		this.tipoLance = tipoLance;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Lance [minuto=" + minuto + ", tipoLance=" + tipoLance + ", comentario=" + comentario + "]";
	}

}
