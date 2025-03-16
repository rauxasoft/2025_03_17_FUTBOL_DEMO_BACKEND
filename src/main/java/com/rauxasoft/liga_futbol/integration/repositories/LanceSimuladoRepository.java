package com.rauxasoft.liga_futbol.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rauxasoft.liga_futbol.business.model.LanceSimulado;

public interface LanceSimuladoRepository extends JpaRepository<LanceSimulado, Long>{

	List<LanceSimulado> findByMinuto(int minuto);
	
}
