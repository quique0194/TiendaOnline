package pe.edu.ucsp.oms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.ucsp.oms.repository.TaskDao;

@Service
public class TaskServiceImpl {
	
	@Autowired
	TaskDao taskDao;

}
