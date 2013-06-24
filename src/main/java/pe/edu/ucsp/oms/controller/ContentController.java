package pe.edu.ucsp.oms.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.repository.ContentDao;

@RequestMapping("/Content")
public class ContentController {

	ContentDao contentDao;
	
	@RequestMapping("/list.html")
	public ModelAndView list(){
		ModelAndView view = new ModelAndView();
		view.setViewName("Content/list");
		view.addObject("Contents",contentDao.findAll());
		
		return view;
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable int id)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("/Content/details");
		view.addObject("content", contentDao.find(id));
		return view;
	}
	
/*
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
		view.addObject("producto", new Content());
		view.setViewName("producto/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("producto") Content producto, BindingResult result, SessionStatus status) {
		if (producto.getId() == null) {
			dao.save(producto);
			status.setComplete();
		}
		else {
			dao.update(producto);
			status.setComplete();
		}
		return list();
	}*/
	
}
