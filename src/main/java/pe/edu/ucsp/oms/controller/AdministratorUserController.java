package pe.edu.ucsp.oms.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.UserDao;

@Controller
@RequestMapping("/Administrator/User")
public class AdministratorUserController {
	
	@Inject
	UserDao userDao;
	
	@RequestMapping("/administrate.html")
	public ModelAndView administrate(){
		ModelAndView view = new ModelAndView();
		List<User> users=userDao.findAll();
		view.addObject("users", users);
		view.setViewName("Administrator/User/administrate");
		return view;
	}
	
	@RequestMapping("/{idUser}/{charge}/charge.html")
	public String charge(@PathVariable Long idUser, @PathVariable Long charge){
		userDao.charge(idUser, charge);
		return "redirect:../../administrate.html";
	}
	
	
}
