package pe.edu.ucsp.oms.service;

import java.util.List;

import pe.edu.ucsp.oms.domain.Administrator;

public interface AdministratorService {

	Administrator findByEmail(String email);
	List<Administrator> filterByEmail(String email);
}
