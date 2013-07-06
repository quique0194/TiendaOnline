package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.Administrator;


public interface AdministratorDao extends GenericDao<Administrator, Long> {
	
	Administrator findByEmail(String email);
	List<Administrator> filterByEmail(String email);
	Long findIdByUsername(String username);
	boolean existsAdministrator(String username, String password);
}
