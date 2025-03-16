package com.rauxasoft.liga_futbol.business.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rauxasoft.liga_futbol.business.model.TipoLance;
import com.rauxasoft.liga_futbol.business.services.TipoLanceServices;
import com.rauxasoft.liga_futbol.integration.repositories.TipoLanceRepository;

@Service
public class TipoLanceServicesImpl implements TipoLanceServices {

	private final TipoLanceRepository tipoLanceRepository;
	
	public TipoLanceServicesImpl(TipoLanceRepository tipoLanceRepository) {
		this.tipoLanceRepository = tipoLanceRepository;
	}
	
	@Override
	public void create(TipoLance tipoLance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TipoLance> read(String nombre) {
		return tipoLanceRepository.findById(nombre);
	}

	@Override
	public List<TipoLance> getAll() {
		return tipoLanceRepository.findAll();
	}

}
