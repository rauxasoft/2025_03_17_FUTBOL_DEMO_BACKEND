package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<?> createArbitro(@RequestBody Arbitro arbitro, UriComponentsBuilder ucb){
		
		Long id = null;
		
		try {
			id = arbitroServices.create(arbitro);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return ResponseEntity.created(ucb.path("/arbitros/{id}").build(id)).build();
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateArbitro(@RequestBody Arbitro arbitro, @PathVariable Long id) {
		
		arbitro.setId(id);
		
		try {
			arbitroServices.update(arbitro);
		} catch(IllegalStateException e) {
			throw new PresentationException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	
}
