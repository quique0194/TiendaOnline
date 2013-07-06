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

import pe.edu.ucsp.oms.domain.SuperAdministrator;
import pe.edu.ucsp.oms.repository.SuperAdministratorDao;

@Controller
@RequestMapping("/SuperAdministrator")
public class SuperAdministratorController {

	@Inject
	SuperAdministratorDao SuperAdmiDao;
		
	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("SuperAdministrator/list", "superadmis", SuperAdmiDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("superadmi", SuperAdmiDao.find(id));
		view.setViewName("SuperAdministrator/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("superadmi", SuperAdmiDao.find(id));
		view.setViewName("SuperAdministrator/edit");
		return view;
	}
	
	@RequestMapping("/{id}/delete.html")
	public ModelAndView delete(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("superadmi", SuperAdmiDao.find(id));
		view.setViewName("SuperAdministrator/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("superadmi", new SuperAdministrator());
		view.setViewName("SuperAdministrator/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("superadmi") SuperAdministrator superadmi, BindingResult result, SessionStatus status) {
		if (superadmi.getId() == null) {
			SuperAdmiDao.save(superadmi);
			status.setComplete();
		}
		else {
			SuperAdmiDao.update(superadmi);
			status.setComplete();
		}
		return list();
	}
}