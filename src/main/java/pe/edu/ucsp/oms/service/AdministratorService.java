package pe.edu.ucsp.oms.service;

import pe.edu.ucsp.oms.domain.Administrator;

public interface AdministratorService {

	Administrator findByEmail(String email);
}
