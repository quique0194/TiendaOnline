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

import pe.edu.ucsp.oms.domain.MediaContent;
import pe.edu.ucsp.oms.repository.MediaContentDao;

@Controller
@RequestMapping("/producto")
public class MediaContentController {

	@Inject
	MediaContentDao dao;

	@RequestMapping("/list.html")
	public ModelAndView list() {
		return new ModelAndView("producto/list", "productos", dao.findAll());
	}

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("producto", dao.find(id));
		view.setViewName("producto/details");
		return view;
	}

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("producto", dao.find(id));
		view.setViewName("producto/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("producto", new MediaContent());
		view.setViewName("producto/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("producto") MediaContent producto, BindingResult result, SessionStatus status) {
		if (producto.getId() == null) {
			dao.save(producto);
			status.setComplete();
		}
		else {
			dao.update(producto);
			status.setComplete();
		}
		return list();
	}
}
