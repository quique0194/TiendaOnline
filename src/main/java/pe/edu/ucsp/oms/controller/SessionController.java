package pe.edu.ucsp.oms.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.AdministratorDao;
import pe.edu.ucsp.oms.repository.SuperAdministratorDao;
import pe.edu.ucsp.oms.repository.UserDao;

@Controller
public class SessionController {

	@Inject
	UserDao userDao;
	@Inject
	AdministratorDao admiDao;
	@Inject
	SuperAdministratorDao superAdmiDao;
	@RequestMapping("/login.html")
	public ModelAndView showLogin() {
		System.out.println("Entramos a login");
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/auth.html", method = RequestMethod.POST)
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println(password);
		PasswordEncoder encoder = new Md5PasswordEncoder();
		password = encoder.encodePassword(password, null);
		System.out.println(password);
		if(userDao.existsUser(username, password)){
			request.getSession().setAttribute("id_user", userDao.findIdByUsername(String.valueOf(username)));
			request.getSession().setAttribute("username", String.valueOf(username));
			response.sendRedirect("home.html");
		}
		else
		{
			if(superAdmiDao.existsSuperAdministrator(username, password)){
				request.getSession().setAttribute("id_admi", superAdmiDao.findIdByUsername(String.valueOf(username)));
				request.getSession().setAttribute("username", String.valueOf(username));
				response.sendRedirect("homeSuperAdministrator.html");
			}
			else
			{
				if(admiDao.existsAdministrator(username, password)){
					request.getSession().setAttribute("id_admi", admiDao.findIdByUsername(String.valueOf(username)));
					request.getSession().setAttribute("username", String.valueOf(username));
					response.sendRedirect("homeAdministrator.html");
				}
				else{
				request.getSession().setAttribute("username", null);
				}
			}
			
		}
		

		
	}

	@RequestMapping("/logout.html")
	public void logout(HttpServletResponse response, HttpServletRequest request) throws IOException {
		request.getSession().setAttribute("username", null);
//		response.sendRedirect("home.html");
	}

	@RequestMapping("/home.html")
	public ModelAndView home(HttpServletResponse response) throws IOException {
		return new ModelAndView("home"); 
	}
	
	@RequestMapping("/homeAdministrator.html")
	public ModelAndView homeAdministrator(HttpServletResponse response) throws IOException {
		return new ModelAndView("homeAdministrator"); 
	}
	
	@RequestMapping("/homeSuperAdministrator.html")
	public ModelAndView homeSuperAdministrator(HttpServletResponse response) throws IOException {
		return new ModelAndView("homeSuperAdministrator"); 
	}
	
	@RequestMapping("/Task.html")
	public ModelAndView task(HttpServletResponse response) throws IOException {
		return new ModelAndView("Task"); 
	}
	
	@RequestMapping("/register.html")
	public ModelAndView register(HttpServletResponse response) throws IOException {
		return new ModelAndView("register");
	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public void save(@ModelAttribute("user") User user, BindingResult result, SessionStatus status , 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
			user.setBalance(0.0);
			user.setState(true);
			user.setPoints(0);
			userDao.save(user);
			status.setComplete();
		
		request.getSession().setAttribute("id_user", userDao.findIdByUsername(user.getUsername()));
		request.getSession().setAttribute("username", String.valueOf(user.getUsername()));
		response.sendRedirect("home.html");
	
	}

}
