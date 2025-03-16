package com.rauxasoft.liga_futbol.presentation.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rauxasoft.liga_futbol.business.model.Arbitro;
import com.rauxasoft.liga_futbol.business.services.ArbitroServices;

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
		return arbitroServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Arbitro getById(@PathVariable Long id) {
		return arbitroServices.read(id).get();
	}
}
