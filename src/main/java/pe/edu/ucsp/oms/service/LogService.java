package pe.edu.ucsp.oms.service;

import java.util.List;

import pe.edu.ucsp.oms.domain.Log;

public interface LogService {
	
	List<Log> filterByAdministrator(Long idAdministrator);
	List<Log> filterByTask(Long idTask);
}
