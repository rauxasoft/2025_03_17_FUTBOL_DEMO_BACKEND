package com.rauxasoft.liga_futbol.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rauxasoft.liga_futbol.business.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long>{

}
