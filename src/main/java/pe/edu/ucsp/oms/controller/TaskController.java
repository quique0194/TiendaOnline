package pe.edu.ucsp.oms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.Task;
import pe.edu.ucsp.oms.repository.TaskDao;

@Controller
@RequestMapping("/Task")
public class TaskController {
	
	@Inject
	TaskDao taskDao;
	
	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("Task/list", "tasks", taskDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("task", taskDao.find(id));
		view.setViewName("Task/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("task", taskDao.find(id));
		view.setViewName("Task/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("task", new Task());
		view.setViewName("Task/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("task") Task task, BindingResult result, SessionStatus status) {
		if (task.getId() == null) {
			taskDao.save(task);
			status.setComplete();
		}
		else {
			taskDao.update(task);
			status.setComplete();
		}
		return list();
	}

}
