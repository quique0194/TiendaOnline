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
import pe.edu.ucsp.oms.domain.Content;
import pe.edu.ucsp.oms.domain.Promo;
import pe.edu.ucsp.oms.domain.TypeContent;
import pe.edu.ucsp.oms.domain.TypeFile;
import pe.edu.ucsp.oms.repository.CategoryDao;
import pe.edu.ucsp.oms.repository.ContentDao;
import pe.edu.ucsp.oms.repository.PromoDao;
import pe.edu.ucsp.oms.repository.TypeContentDao;
import pe.edu.ucsp.oms.repository.TypeFileDao;

@Controller
@RequestMapping("/Administrator/Content")
public class AdministratorContentController {

	@Inject 
	ContentDao contentDao;
	
	@Inject 
	CategoryDao categoryDao;
	
	@Inject 
	PromoDao promoDao;
	
	@Inject 
	TypeFileDao typeFileDao;
	
	@Inject 
	TypeContentDao typeContentDao;
	
	
	@RequestMapping("/administrate.html")
	public ModelAndView administrate(){
		ModelAndView view = new ModelAndView();
		view.addObject("contents", contentDao.findAll());
		view.setViewName("Administrator/Content/administrate");
		return view;
	}
	

	@RequestMapping("/{id}/delete.html")
	public String delete(@PathVariable Long id){
		contentDao.removeById(id);
		return "redirect:../administrate.html";
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id){
		ModelAndView view= new ModelAndView();
		Content content=contentDao.find(id);
		view.addObject("content",content);
		Category category=categoryDao.find(content.getIdCategory());
		view.addObject("category", category);
		view.addObject("parents",categoryDao.findParents(category.getId()));
		Promo promo=promoDao.find(content.getIdPromo());
		view.addObject("promo",promo);
		TypeFile typeFile = typeFileDao.find(content.getIdTypeFile());
		view.addObject("typeFile", typeFile);
		TypeContent typeContent=typeContentDao.find(typeFile.getIdTypeContent());
		view.addObject("typeContent", typeContent);
		view.setViewName("Administrator/Content/details");
		return view;
	}
	
	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("content", new Content());
		view.addObject("categories", categoryDao.findAll());
		view.setViewName("Administrator/Content/edit");
		return view;
	}
	
	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("content", contentDao.find(id));
		view.addObject("categories", categoryDao.findAll());
		view.addObject("promos", promoDao.getValidPromos());
		view.addObject("typeFiles", typeFileDao.findAll());
		view.setViewName("Administrator/Content/edit");
		return view;
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("content") Content content, BindingResult result, SessionStatus status) {
		if (content.getId() == null) {
			contentDao.save(content);
			status.setComplete();
		}
		else {
			System.out.println(content.toString());
			contentDao.update(content);
			status.setComplete();
		}
		return administrate();
	}
}
