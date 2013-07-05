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

import pe.edu.ucsp.oms.domain.Category;
import pe.edu.ucsp.oms.repository.CategoryDao;

@Controller
@RequestMapping("/Category")
public class CategoryController{
	
	@Inject
	CategoryDao categoryDao;
	
	@RequestMapping("/list.html")
	public ModelAndView list(){
		return new ModelAndView("Category/list" ,"categories" , categoryDao.findAll());	
	}
	

	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.setViewName("Category/details");
		view.addObject("category", categoryDao.find(id));
		view.addObject("parent" , categoryDao.findParent(id));
		return view;
	}
	
	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id){
		ModelAndView view =  new ModelAndView();
		view.addObject("category" , categoryDao.find(id) );
		view.setViewName("Category/edit");
		return view;
	}
	
	@RequestMapping("/add.html")
	public ModelAndView add(){
		ModelAndView view = new ModelAndView();
		view.addObject("category" , new Category());
		view.setViewName("Category/edit");
		return view;
	}
	
	@RequestMapping(value = "/save.html" , method = RequestMethod.POST)
	public ModelAndView save (@ModelAttribute("category") Category category , BindingResult result, SessionStatus status){
		if(category.getId() == null){
			System.out.println("Insertando Categoria");
			System.out.println(category.getName() + "  " + category.getIdFather());
			categoryDao.save(category);
			status.setComplete();
		}
		else {
			categoryDao.update(category);
			status.setComplete();
		}
		return list();
	}
}
