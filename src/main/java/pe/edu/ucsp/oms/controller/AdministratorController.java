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

import pe.edu.ucsp.oms.domain.Administrator;
import pe.edu.ucsp.oms.repository.AdministratorDao;


@Controller
@RequestMapping("/Administrator")
public class AdministratorController {

	@Inject
	AdministratorDao admiDao;

	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("Administrator/list", "admis", admiDao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("admi", admiDao.find(id));
		view.setViewName("Administrator/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("admi", admiDao.find(id));
		view.setViewName("Administrator/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("admi", new Administrator());
		view.setViewName("Administrator/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("admi") Administrator admi, BindingResult result, SessionStatus status) {
		if (admi.getId() == null) {
			admiDao.save(admi);
			status.setComplete();
		}
		else {
			admiDao.update(admi);
			status.setComplete();
		}
		return list();
	}
}
