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

import pe.edu.ucsp.oms.domain.Log;
import pe.edu.ucsp.oms.repository.LogDao;

@Controller
@RequestMapping("/Log")
public class LogController {
	@Inject
	LogDao logDao;

	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("Log/list", "logs", logDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("log", logDao.find(id));
		view.setViewName("Log/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("log", logDao.find(id));
		view.setViewName("Log/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("log", new Log());
		view.setViewName("Log/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("log") Log log, BindingResult result, SessionStatus status) {
		if (log.getId() == null) {
			logDao.save(log);
			status.setComplete();
		}
		else {
			logDao.update(log);
			status.setComplete();
		}
		return list();
	}

}
