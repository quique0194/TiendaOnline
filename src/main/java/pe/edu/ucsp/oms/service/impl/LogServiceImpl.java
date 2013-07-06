package pe.edu.ucsp.oms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.ucsp.oms.domain.Log;
import pe.edu.ucsp.oms.repository.LogDao;
import pe.edu.ucsp.oms.service.LogService;

@Service
public class LogServiceImpl implements LogService{
	
	@Autowired
	LogDao logDao;

	@Override
	public List<Log> filterByAdministrator(Long idAdministrator) {
		return logDao.filterByAdministrator(idAdministrator);
	}

	@Override
	public List<Log> filterByTask(Long idTask) {
		return logDao.filterByTask(idTask);
	}
	
}
