package com.rauxasoft.liga_futbol.business.services;

import java.util.Map;

import com.rauxasoft.liga_futbol.business.model.dtos.EstadisticasEquipo;

public interface EstadisticaServices {

	Map<Long, EstadisticasEquipo> getClasificacion();
	
}
