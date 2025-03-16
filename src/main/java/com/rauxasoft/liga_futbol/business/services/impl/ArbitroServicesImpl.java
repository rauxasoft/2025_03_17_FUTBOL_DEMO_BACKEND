package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.Arbitro;
import com.rauxasoft.liga_futbol.business.services.ArbitroServices;
import com.rauxasoft.liga_futbol.integration.repositories.ArbitroRepository;

@Service
public class ArbitroServicesImpl implements ArbitroServices {

	private final ArbitroRepository arbitroRepository;
	
	public ArbitroServicesImpl(ArbitroRepository arbitroRepository) {
		this.arbitroRepository =  arbitroRepository;
	}
	
	@Override
	public Optional<Arbitro> read(Long idArbitro) {
		return arbitroRepository.findById(idArbitro);
	}

	@Override
	public List<Arbitro> getAll() {
		return arbitroRepository.findAll();
	}

}
