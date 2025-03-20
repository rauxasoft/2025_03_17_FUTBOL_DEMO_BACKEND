package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.dtos.EstadisticasEquipo;
import com.rauxasoft.liga_futbol.business.services.EstadisticaServices;

@RestController
@CrossOrigin
@RequestMapping("/clasificaciones")
public class ClasificacionController {

	private EstadisticaServices estadisticaServices;
	
	public ClasificacionController(EstadisticaServices estadisticaServices) {
		this.estadisticaServices = estadisticaServices;
	}
	
	@GetMapping
	public Object getClasificacion() {
		Map<Long, EstadisticasEquipo> estadisticas = estadisticaServices.getClasificacion();
		return estadisticas;
	}
	
}
