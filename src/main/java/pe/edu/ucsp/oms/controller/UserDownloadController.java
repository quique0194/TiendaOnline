package pe.edu.ucsp.oms.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.repository.ContentDao;
import pe.edu.ucsp.oms.repository.DownloadDao;
import pe.edu.ucsp.oms.repository.UserDao;


@Controller
@RequestMapping("/User/Download")
public class UserDownloadController {
	
	@Inject 
	UserDao userDao;
	
	@Inject 
	DownloadDao downloadDao;
	
	
	
	@RequestMapping("/record.html")
	public ModelAndView record(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.addObject("downloads", downloadDao.filterByUser((Long)request.getSession().getAttribute("id_user")) );
		view.setViewName("User/Download/record");
		return view;
	}
	
	

}
