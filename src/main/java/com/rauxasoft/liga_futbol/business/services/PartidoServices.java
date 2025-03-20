package com.rauxasoft.liga_futbol.business.services;

import java.util.List;
import java.util.Optional;

import com.rauxasoft.liga_futbol.business.model.Lance;
import com.rauxasoft.liga_futbol.business.model.Partido;

public interface PartidoServices {

	List<Partido> getAll();
	List<Partido> getPartidosNoPendientes();
	
	Optional<Partido> read(Long id);
	
	List<Lance> getLances(Long idPartido);
	
	void addLance(Long idPartido, Lance lance);
	
	List<Object[]> getEstadisticasPartidos();
}
