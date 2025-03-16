package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.Equipo;
import com.rauxasoft.liga_futbol.business.services.EquipoServices;
import com.rauxasoft.liga_futbol.integration.repositories.EquipoRepository;

@Service
public class EquipoServicesImpl implements EquipoServices {

	private final EquipoRepository equipoRepository;
	
	public EquipoServicesImpl(EquipoRepository equipoRepository) {
		this.equipoRepository = equipoRepository;
	}
	
	@Override
	public List<Equipo> getAll() {
		return equipoRepository.findAll();
	}

	@Override
	public Long create(Equipo equipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Equipo> read(Long idEquipo) {
		return equipoRepository.findById(idEquipo);
	}

}
