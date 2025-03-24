package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.Arbitro;
import com.rauxasoft.liga_futbol.business.services.ArbitroServices;
import com.rauxasoft.liga_futbol.integration.repositories.ArbitroRepository;

import jakarta.transaction.Transactional;

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

	@Override
	@Transactional
	public Long create(Arbitro arbitro) {
	
		if (arbitro.getId() != null) {
			throw new RuntimeException("Para crear un árbitro su ID ha de ser null");
		}
		
		Arbitro createdArbitro = arbitroRepository.save(arbitro);
		
		return createdArbitro.getId();
	}

	@Override
	@Transactional
	public void update(Arbitro arbitro) {

		Long id = arbitro.getId(); 
		
		boolean existe = arbitroRepository.existsById(id);
		
		if(!existe) {
			throw new IllegalStateException("El árbitro con ID [" + id + "] no existe.");
		}
		
		arbitroRepository.save(arbitro);
		
	}


}
