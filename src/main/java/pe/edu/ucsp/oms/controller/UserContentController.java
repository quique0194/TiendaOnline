package pe.edu.ucsp.oms.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.Category;
import pe.edu.ucsp.oms.domain.Content;
import pe.edu.ucsp.oms.domain.Download;
import pe.edu.ucsp.oms.domain.Payment;
import pe.edu.ucsp.oms.domain.Promo;
import pe.edu.ucsp.oms.domain.TypeContent;
import pe.edu.ucsp.oms.domain.TypeFile;
import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.CategoryDao;
import pe.edu.ucsp.oms.repository.ContentDao;
import pe.edu.ucsp.oms.repository.DownloadDao;
import pe.edu.ucsp.oms.repository.PaymentDao;
import pe.edu.ucsp.oms.repository.PromoDao;
import pe.edu.ucsp.oms.repository.TypeContentDao;
import pe.edu.ucsp.oms.repository.TypeFileDao;
import pe.edu.ucsp.oms.repository.UserDao;

@Controller
@RequestMapping("/User/Content")
public class UserContentController {
	
	@Inject
	PaymentDao paymentDao;
	
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
	
	@Inject 
	UserDao userDao;
	
	@Inject 
	DownloadDao downloadDao;
	
	
	
	@RequestMapping("/genericList.html")
	public ModelAndView genericList() {
		ModelAndView view = new ModelAndView();
		view.addObject("message","");
		view.addObject("genericContents", contentDao.findAll());
		view.setViewName("User/Content/genericList");
		return view;
	}
	
	
	@RequestMapping("/paidList.html")
	public ModelAndView paidList(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.addObject("message","");
		view.addObject("paidContents",paymentDao.filterByIdUser((Long)request.getSession().getAttribute("id_user")));
		view.setViewName("User/Content/paidList");
		return view;
	}
	

	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
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
		view.setViewName("User/Content/details");
		return view;
	}
	
	@RequestMapping("/{id}/buy.html")
	public ModelAndView buy(@PathVariable Long id,HttpServletRequest request) {
		Content content = contentDao.find(id);
		User user = userDao.find((Long)request.getSession().getAttribute("id_user"));
		if(!paymentDao.exists(user.getId(), content.getId()))
		{
			if(content.getPrice() < user.getBalance() )
			{
				user.setBalance(user.getBalance() - content.getPrice() );
				Payment payment = new Payment();
				payment.setIdContent(content.getId());
				payment.setIdUser(user.getId());
				userDao.updateBalance(user);
				
				paymentDao.save(payment);
				ModelAndView view = new ModelAndView();
				view.addObject("message","Compra realizada exitosamente");
				view.addObject("paidContents",paymentDao.filterByIdUser((Long)request.getSession().getAttribute("id_user")));
				view.setViewName("User/Content/paidList");
				return view;
			}
			else
			{
				ModelAndView view = new ModelAndView();
				view.addObject("message","Saldo insuficiente");
				view.addObject("genericContents", contentDao.findAll());
				view.setViewName("User/Content/genericList");
				return view;
			}
		}
		
		
		
		
		ModelAndView view = new ModelAndView();
		view.addObject("message","usted ya cuenta con este contenido");
		view.addObject("genericContents", contentDao.findAll());
		view.setViewName("User/Content/genericList");
		return view;
		
	}
	
	@RequestMapping("/{id}/download.html")
	public ModelAndView down(@PathVariable Long id,HttpServletRequest request) {
		
		Date date = new Date();
		Download download = new Download();
		download.setDate(String.valueOf(date));
		download.setIdContent(id);
		download.setIdUser((Long)request.getSession().getAttribute("id_user"));
		downloadDao.save(download);
		ModelAndView view = new ModelAndView();
		view.addObject("downloads", downloadDao.filterByUser((Long)request.getSession().getAttribute("id_user")) );
		view.setViewName("User/Download/record");
		return view;
	}

}
