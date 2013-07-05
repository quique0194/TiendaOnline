package pe.edu.ucsp.oms.repository;

import java.util.List;

import pe.edu.ucsp.oms.domain.Administrator;
import pe.edu.ucsp.oms.domain.Task;

public interface LogAdministratorDao {
	
	List<Administrator> filterByAdministrator(int id_admi);
	
	List<Task> filterByTask(int id_task);
	
}
