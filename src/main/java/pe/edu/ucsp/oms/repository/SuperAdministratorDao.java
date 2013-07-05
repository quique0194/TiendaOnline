package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.SuperAdministrator;

public interface SuperAdministratorDao extends GenericDao<SuperAdministrator, Long> {
	
	SuperAdministrator findByUsername(String username);
	List<SuperAdministrator> filterByUsername(String username);
	boolean existsSuperAdministrator(String username, String password);
	
}
