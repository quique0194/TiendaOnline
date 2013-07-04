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

import pe.edu.ucsp.oms.domain.TypeContent;
import pe.edu.ucsp.oms.repository.TypeContentDao;

@Controller
@RequestMapping("/TypeContent")
public class TypeContentController {
	
	@Autowired
	TypeContentDao tcDao;
	

	@RequestMapping("/list.html")
	public @ResponseBody
	ModelAndView list(){
		ModelAndView view = new ModelAndView();
		view.setViewName("TypeContent/list");
		view.addObject("tcs",tcDao.findAll());
		return view;
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("TypeContent/details");
		view.addObject("tc", tcDao.find(id));
		
		return view;
	}
	

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("tc", tcDao.find(id));
		view.setViewName("TypeContent/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("tc", new TypeContent());
		view.setViewName("TypeContent/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("tc") TypeContent tc, BindingResult result, SessionStatus status) {
		if (tc.getId() == null) {
			tcDao.save(tc);
			status.setComplete();
		}
		else {
			System.out.println(tc.toString());
			tcDao.update(tc);
			status.setComplete();
		}
		return list();
	}
}
