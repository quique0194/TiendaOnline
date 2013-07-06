package pe.edu.ucsp.oms.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.repository.ContentDao;
import pe.edu.ucsp.oms.repository.PaymentDao;

@Controller
@RequestMapping("/User/Content")
public class UserContentController {
	
	@Inject
	PaymentDao paymentDao;
	
	@Inject
	ContentDao contentDao;
	
	
	@RequestMapping("/genericList.html")
	public ModelAndView genericList() {
		return new ModelAndView("User/Content/genericList", "genericContents", contentDao.findAll());
	}
	
	@RequestMapping("/paidList.html")
	public ModelAndView paidList(HttpServletRequest request) {
		return new ModelAndView("User/Content/paidList", "paidContents", paymentDao.filterByIdUser((Long)request.getSession().getAttribute("id_user")));
	}

}
