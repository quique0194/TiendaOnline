package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.Log;

public interface LogDao  extends GenericDao<Log, Long> {
	
	List<Log> filterByAdministrator(Long idAdministrator);
	
	List<Log> filterByTask(Long idTask);
	
}
