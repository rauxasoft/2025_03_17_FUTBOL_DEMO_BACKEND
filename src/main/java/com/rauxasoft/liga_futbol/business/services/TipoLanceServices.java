package com.rauxasoft.liga_futbol.business.services;

import java.util.List;
import java.util.Optional;

import com.rauxasoft.liga_futbol.business.model.TipoLance;

public interface TipoLanceServices {
	
	void create(TipoLance tipoLance);
	Optional<TipoLance> read(String nombre);
	
	List<TipoLance> getAll();
	
	
}
