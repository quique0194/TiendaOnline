package pe.edu.ucsp.oms.repository;


import pe.edu.ucsp.oms.domain.SuperAdministrator;

public interface SuperAdministratorDao extends GenericDao<SuperAdministrator, Long> {

	Long findIdByUsername(String username);

	SuperAdministrator findByUsername(String username);
	boolean existsSuperAdministrator(String username, String password);
	
}
