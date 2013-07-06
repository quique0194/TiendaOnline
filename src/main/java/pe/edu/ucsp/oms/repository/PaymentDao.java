package pe.edu.ucsp.oms.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import pe.edu.ucsp.oms.domain.Payment;


@Repository
public interface PaymentDao extends GenericDao<Payment, Long>{

	List<Payment> filterByIdUser(Long idUser);
	List<Payment> filterByIdContent(Long idContent);
	boolean exists(Long idUser ,Long idContent);
	//List<Content> findByIdUser(Long idUser);

}
