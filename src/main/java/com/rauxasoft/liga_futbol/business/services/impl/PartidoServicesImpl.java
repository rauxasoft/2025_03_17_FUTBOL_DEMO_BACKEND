package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.Lance;
import com.rauxasoft.liga_futbol.business.model.Partido;
import com.rauxasoft.liga_futbol.business.services.PartidoServices;
import com.rauxasoft.liga_futbol.integration.repositories.PartidoRepository;

@Service
public class PartidoServicesImpl implements PartidoServices {

	private final PartidoRepository partidoRepository;
	
	public PartidoServicesImpl(PartidoRepository partidoRepository) {
		this.partidoRepository = partidoRepository;
	}
	
	@Override
	public List<Partido> getAll() {
		return partidoRepository.findAll();
	}

	@Override
	public Optional<Partido> read(Long id) {
		return partidoRepository.findById(id);
	}

	@Override
	public List<Lance> getLances(Long idPartido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLance(Long idPartido, Lance lance) {
		// TODO Auto-generated method stub
		
	}

}
