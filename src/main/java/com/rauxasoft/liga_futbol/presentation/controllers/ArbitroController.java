package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.Arbitro;
import com.rauxasoft.liga_futbol.business.services.ArbitroServices;
import com.rauxasoft.liga_futbol.presentation.config.PresentationException;

@RestController
@CrossOrigin
@RequestMapping("/arbitros")
public class ArbitroController {

	private final ArbitroServices arbitroServices;
	
	public ArbitroController(ArbitroServices arbitroServices) {
		this.arbitroServices = arbitroServices;
	}
	
	@GetMapping
	public List<Arbitro> getAll(){
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
		
		}
		
		return arbitroServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Arbitro getArbitro(@PathVariable Long id) {

		Optional<Arbitro> optional = arbitroServices.read(id);
		
		if(optional.isEmpty()) {
			throw new PresentationException("No existe el árbitro con ID " + id, HttpStatus.NOT_FOUND);
		}
		
		return optional.get();
	}
}
