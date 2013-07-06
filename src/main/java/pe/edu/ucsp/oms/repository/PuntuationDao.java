package pe.edu.ucsp.oms.repository;

import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Puntuation;

@Repository
public interface PuntuationDao extends GenericDao<Puntuation, Long> {
	
}
