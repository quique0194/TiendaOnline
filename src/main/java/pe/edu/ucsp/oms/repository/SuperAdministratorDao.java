package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.SuperAdministrator;

public interface SuperAdministratorDao extends GenericDao<SuperAdministrator, Long> {
	
	SuperAdministrator findByUser(String user);
	List<SuperAdministrator> filterByUser(String user);
	boolean existsSuperAdministrator(String username, String password);
	
}
