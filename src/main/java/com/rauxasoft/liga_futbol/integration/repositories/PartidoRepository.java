package com.rauxasoft.liga_futbol.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rauxasoft.liga_futbol.business.model.Partido;

public interface PartidoRepository extends JpaRepository<Partido, Long>{

	@Query("SELECT p FROM Partido p where p.estado != 'PENDIENTE'")
	List<Partido> getPartidosNoPendientes();
	
	@Query(value = """
			
				SELECT P.ID AS PARTIDO,
               		   P.ID_EQUIPO_LOCAL,
               		   P.ID_EQUIPO_VISITANTE,
               		   ID_LANCE
                  FROM LANCES L RIGHT JOIN PARTIDOS P ON (L.ID_PARTIDO = P.ID)
                 WHERE ID_LANCE IN ('GOL_VISITANTE','GOL_LOCAL', 'GOL_LOCAL_ANULADO', 'GOL_VISITANTE_ANULADO') 
              ORDER BY P.ID
			
			
			       """, 
	
			nativeQuery = true)
	List<Object[]> getEstadisticaPartidos();
	
}
