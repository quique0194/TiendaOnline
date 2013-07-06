package pe.edu.ucsp.oms.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.repository.AdministratorDao;
import pe.edu.ucsp.oms.repository.SuperAdministratorDao;
import pe.edu.ucsp.oms.repository.UserDao;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Inject
	UserDao userDao;
	@Inject
	AdministratorDao admiDao;
	@Inject
	SuperAdministratorDao superAdmiDao;
	
	@Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
	  return true;
  }

	@Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
		String username = (String)request.getSession().getAttribute("username");
		System.out.println(username);
		if (username == null) {
			if (!request.getRequestURL().toString().endsWith("login.html") && 
				!request.getRequestURL().toString().endsWith("register.html")){
				response.sendRedirect(request.getContextPath() + "/login.html");
			}
		}
		
		else{
			if(userDao.existsByUsername(username)){
				System.out.println("es un usuario!");
				if(!request.getRequestURL().toString().endsWith("login.html")&& 
				   !request.getRequestURL().toString().endsWith("register.html") &&
				   !request.getRequestURL().toString().endsWith("save.html") &&
				   !request.getRequestURL().toString().endsWith("auth.xml") &&
				   request.getRequestURL().toString().contains("Administrator")){
					System.out.println("no puedes!");
					response.sendRedirect(request.getContextPath() + "/home.html");
				}
			}
			else{
				if(admiDao.existsByUsername(username)){
					System.out.println("es un administrador");
					if(!request.getRequestURL().toString().endsWith("login.html")&& 
						!request.getRequestURL().toString().endsWith("register.html") &&
					   !request.getRequestURL().toString().endsWith("save.html") &&
					   !request.getRequestURL().toString().endsWith("auth.xml") &&
					   (request.getRequestURL().toString().contains("Super") ||
					   request.getRequestURL().toString().contains("User") ) ){
						response.sendRedirect(request.getContextPath() + "/homeAdministrator.html");
					}
					else {
						if(superAdmiDao.existsByUsername(username)){
							System.out.println("es el Supremo Kaiosama!!");
							if(!request.getRequestURL().toString().endsWith("login.html")&& 
									!request.getRequestURL().toString().endsWith("register.html") &&
								   !request.getRequestURL().toString().endsWith("save.html") &&
								   !request.getRequestURL().toString().endsWith("auth.xml") ){
									response.sendRedirect(request.getContextPath() + "/homeAdministrator.html");
								}
						}
					}
				}
			}
		}
  }

	@Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
  }

}
