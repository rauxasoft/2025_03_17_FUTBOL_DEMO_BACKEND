package com.rauxasoft.liga_futbol.business.model.dtos;

import java.io.Serializable;
import java.util.Date;

public class LanceDTO implements Serializable{
	private static final long serialVersionUID = 1L;
		
	private Long idPartido;
	private String tipoLance;
	private Date fecha;
	private Integer minuto;
	private String comentario;
		
	public LanceDTO() {
			
	}
		
	public LanceDTO(Long idPartido, String tipoLance, Date fecha, Integer minuto, String comentario) {
		this.idPartido = idPartido;
		this.tipoLance = tipoLance;
		this.fecha = fecha;
		this.minuto = minuto;
		this.comentario = comentario;
	}

	public Long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(Long idPartido) {
		this.idPartido = idPartido;
	}
	
	public String getTipoLance() {
		return tipoLance;
	}

	public void setTipoLance(String tipoLance) {
		this.tipoLance = tipoLance;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
		
	public Integer getMinuto() {
		return minuto;
	}

	public void setMinuto(Integer minuto) {
		this.minuto = minuto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
}
