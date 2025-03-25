package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.Equipo;
import com.rauxasoft.liga_futbol.business.services.EquipoServices;
import com.rauxasoft.liga_futbol.presentation.config.PresentationException;

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
	
	@GetMapping("/{id}")
	public Equipo getEquipo(@PathVariable Long id) {
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		Optional<Equipo> optional = equipoServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el equipo con ID " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
	
}
