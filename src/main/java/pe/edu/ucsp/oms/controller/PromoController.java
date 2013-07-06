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

import pe.edu.ucsp.oms.domain.Promo;
import pe.edu.ucsp.oms.repository.PromoDao;

@Controller
@RequestMapping("/Promo")
public class PromoController {
	
	@Inject
	PromoDao promoDao;
	
	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("Promo/list", "promos", promoDao.findAll());
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("promo", promoDao.find(id));
		view.setViewName("Promo/details");
		return view;
	}
	
	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("promo", promoDao.find(id));
		view.setViewName("Promo/edit");
		return view;
	}
	
	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("promo", new Promo());
		view.setViewName("Promo/edit");
		return view;
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("promo") Promo promo, BindingResult result, SessionStatus status) {
		if (promo.getId() == null) {
			promoDao.save(promo);
			status.setComplete();
		}
		else {
			promoDao.update(promo);
			status.setComplete();
		}
		return list();
	}
}
