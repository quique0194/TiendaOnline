package pe.edu.ucsp.oms.controller;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.Administrator;

import pe.edu.ucsp.oms.repository.AdministratorDao;

@Controller
@RequestMapping("/Administrator")
public class AdministratorController {

	@Inject
	AdministratorDao admiDao;
	
	protected PasswordEncoder encoder = new Md5PasswordEncoder();
	
	@RequestMapping("/info.html")
	public ModelAndView info(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.addObject("administrator", admiDao.find((Long)request.getSession().getAttribute("id_admi")));
		view.setViewName("Administrator/info");
		return view;
	}
	
	@RequestMapping("/edit.html")
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.addObject("admi", admiDao.find((Long)request.getSession().getAttribute("id_admi")));
		view.setViewName("Administrator/edit");
		return view;
	}
	
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("admi") Administrator admi, BindingResult result, SessionStatus status, HttpServletRequest request) {
		if(admi.getPassword()!="")
		{
			admi.setPassword(encoder.encodePassword(admi.getPassword(),null));
		}
		else
		{
			admi.setPassword(admiDao.find(admi.getId()).getPassword());
		}
		admiDao.update(admi);
		status.setComplete();
		return info(request);
	}
	
	
}
