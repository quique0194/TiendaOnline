package pe.edu.ucsp.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.ucsp.oms.domain.SuperAdministrator;
import pe.edu.ucsp.oms.repository.SuperAdministratorDao;
import pe.edu.ucsp.oms.service.SuperAdministratorService;

@Service
public class SuperAdministratorServiceImpl implements SuperAdministratorService{
	@Autowired
	SuperAdministratorDao superAdmiDao;

	@Override
	public SuperAdministrator findByUsername(String email) {
		return superAdmiDao.findByUsername(email);
	}
}
