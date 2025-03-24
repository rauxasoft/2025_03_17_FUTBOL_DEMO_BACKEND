package com.rauxasoft.liga_futbol.business.services;

import java.util.List;
import java.util.Optional;

import com.rauxasoft.liga_futbol.business.model.Arbitro;

public interface ArbitroServices {

	Optional<Arbitro> read(Long idArbitro);
	
	List<Arbitro> getAll();
	
	Long create(Arbitro arbitro);
	
	/**
	 * Si la id es null o no existe lanza IllegalStateException
	 * 
	 */
	void update(Arbitro producto);
	
}
