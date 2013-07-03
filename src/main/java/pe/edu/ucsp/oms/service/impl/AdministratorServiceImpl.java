package pe.edu.ucsp.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.ucsp.oms.domain.Administrator;
import pe.edu.ucsp.oms.repository.AdministratorDao;
import pe.edu.ucsp.oms.service.AdministratorService;


@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	AdministratorDao admiDao;

	@Override
	public Administrator findByEmail(String email) {
		return admiDao.findByEmail(email);
	}

}
