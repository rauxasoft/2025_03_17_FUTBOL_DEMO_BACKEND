package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.Partido;
import com.rauxasoft.liga_futbol.business.services.PartidoServices;
import com.rauxasoft.liga_futbol.presentation.config.PresentationException;

@RestController
@CrossOrigin
@RequestMapping("/partidos")
public class PartidoController {
	
	private final PartidoServices partidoServices;
	
	public PartidoController(PartidoServices partidoServices) {
		this.partidoServices = partidoServices;
	}
	
	@GetMapping
	public List<Partido> getAll(){
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			
		}	
		
		return partidoServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Partido getPartido(@PathVariable Long id) {
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			
		}
		
		Optional<Partido> optional = partidoServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el partido con ID " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}

}
