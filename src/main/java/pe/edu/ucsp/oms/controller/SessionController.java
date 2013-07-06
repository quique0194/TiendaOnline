package pe.edu.ucsp.oms.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.repository.AdministratorDao;
import pe.edu.ucsp.oms.repository.UserDao;

@Controller
public class SessionController {

	@Inject
	UserDao userDao;
	@Inject
	AdministratorDao admiDao;
	
	@RequestMapping("/login.html")
	public ModelAndView showLogin() {
		System.out.println("Entramos a login");
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/auth.html", method = RequestMethod.POST)
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(userDao.existsUser(username, password)){
			request.getSession().setAttribute("id_user", userDao.findIdByUsername(String.valueOf(username)));
			request.getSession().setAttribute("username", String.valueOf(username));
			response.sendRedirect("home.html");
		}
		else
		{
			if(admiDao.existsAdministrator(username, password)){
				request.getSession().setAttribute("username", String.valueOf(username));
				response.sendRedirect("homeAdministrator.html");
			}
			else
			request.getSession().setAttribute("username", null);
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
	
	@RequestMapping("/Task.html")
	public ModelAndView task(HttpServletResponse response) throws IOException {
		return new ModelAndView("Task"); 
	}

}
