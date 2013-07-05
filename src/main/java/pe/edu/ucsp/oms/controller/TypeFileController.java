package pe.edu.ucsp.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


import pe.edu.ucsp.oms.domain.TypeFile;
import pe.edu.ucsp.oms.repository.TypeFileDao;

@Controller
@RequestMapping("/TypeFile")
public class TypeFileController {
	
	@Autowired
	TypeFileDao tfDao;
	

	@RequestMapping("/list.html")
	public @ResponseBody
	ModelAndView list(){
		ModelAndView view = new ModelAndView();
		view.setViewName("TypeFile/list");
		view.addObject("tfs",tfDao.findAll());
		return view;
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("TypeFile/details");
		view.addObject("tf", tfDao.find(id));
		
		return view;
	}
	

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("tf", tfDao.find(id));
		view.setViewName("TypeFile/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("tf", new TypeFile());
		view.setViewName("TypeFile/edit");
		return view;
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("tf") TypeFile tf, BindingResult result, SessionStatus status) {
		if (tf.getId() == null) {
			tfDao.save(tf);
			status.setComplete();
		}
		else {			
			tfDao.update(tf);
			status.setComplete();
		}
		return list();
	}

}
