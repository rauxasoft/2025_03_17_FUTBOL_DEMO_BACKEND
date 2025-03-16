package com.rauxasoft.liga_futbol.business.services;

import java.util.List;
import java.util.Optional;

import com.rauxasoft.liga_futbol.business.model.Equipo;

public interface EquipoServices {

	List<Equipo> getAll();
	
	Long create(Equipo equipo);
	Optional<Equipo> read(Long idEquipo);
}
