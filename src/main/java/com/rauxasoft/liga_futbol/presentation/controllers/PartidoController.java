package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.Partido;
import com.rauxasoft.liga_futbol.business.services.PartidoServices;

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
		return partidoServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Partido read(@PathVariable Long id) {
		
		Optional<Partido> optional = partidoServices.read(id);
		
		return optional.get();
		
	}

}
