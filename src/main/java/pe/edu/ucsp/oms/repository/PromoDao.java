package pe.edu.ucsp.oms.repository;

import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Promo;

@Repository 
public interface PromoDao extends GenericDao<Promo, Long> {
	
}
