package pe.edu.ucsp.oms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Promo;

@Repository 
public interface PromoDao extends GenericDao<Promo, Long> {
	List<Promo> getValidPromos();
}
