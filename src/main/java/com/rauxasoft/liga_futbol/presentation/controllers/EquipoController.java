package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.Equipo;
import com.rauxasoft.liga_futbol.business.services.EquipoServices;

@RestController
@CrossOrigin
@RequestMapping("/equipos")
public class EquipoController {

	private final EquipoServices equipoServices;
	
	public EquipoController(EquipoServices equipoServices) {
		this.equipoServices = equipoServices;
	}
	
	@GetMapping
	public List<Equipo> getAll(){
		return equipoServices.getAll();
	}
	
}
