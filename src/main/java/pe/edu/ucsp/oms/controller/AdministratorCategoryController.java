package pe.edu.ucsp.oms.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
@RequestMapping("/Administrator/Category")
public class AdministratorCategoryController {


	@Inject 
	CategoryDao categoryDao;
	
	@RequestMapping("/administrate.html")
	public ModelAndView administrate(){
		ModelAndView view = new ModelAndView();
		List<Category> categories=categoryDao.findAll();
		List<List<Category>> parentLists = new ArrayList<List<Category>>();
		Iterator<Category> it=categories.iterator();
        while(it.hasNext())
        {
          parentLists.add(categoryDao.findParents(it.next().getId()));
        }			
		view.addObject("parentLists", parentLists);
		view.addObject("categories", categories);
		view.setViewName("Administrator/Category/administrate");
		return view;
	}
	
	@RequestMapping("/{id}/delete.html")
	public String delete(@PathVariable Long id){
		categoryDao.deleteCategory(id);
		return "redirect:../administrate.html";
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id){
		ModelAndView view= new ModelAndView();
		Category category=categoryDao.find(id);
		view.addObject("category",category);
		view.addObject("parents",categoryDao.findParents(category.getId()));
		view.setViewName("Administrator/Category/details");
		return view;
	}
	
	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("parents", categoryDao.findAll());
		view.addObject("category", categoryDao.find(id));
		view.setViewName("Administrator/Category/edit");
		return view;
	}
	
	@RequestMapping("/add.html")
	public ModelAndView addContent() {
		ModelAndView view = new ModelAndView();
		view.addObject("category", new Category());
		view.addObject("parents", categoryDao.findAll());
		view.setViewName("Administrator/Category/edit");
		return view;
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public String save(@ModelAttribute("category") Category category, BindingResult result, SessionStatus status) {
		if (category.getId() == null) {
			categoryDao.save(category);
			status.setComplete();
		}
		else {
			categoryDao.update(category);
			status.setComplete();
		}
		return "redirect:administrate.html";
	}
}
