package pe.edu.ucsp.oms.service;

import pe.edu.ucsp.oms.domain.SuperAdministrator;

public interface SuperAdministratorService {
	SuperAdministrator findByUsername(String username);

}
