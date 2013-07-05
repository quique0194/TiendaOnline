package pe.edu.ucsp.oms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import pe.edu.ucsp.oms.domain.Notification;
import pe.edu.ucsp.oms.repository.NotificationDao;

@Controller
@RequestMapping("/Notification")
public class NotificationController {
	
	@Autowired
	NotificationDao notificationDao;
	
	@RequestMapping("/list.html")
	public @ResponseBody
	ModelAndView list(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/Notification/list");
		view.addObject("notifications",notificationDao.findAll());
		return view;
	}
	
	@RequestMapping("/{id}/details.html")
	public ModelAndView details(@PathVariable Long id)
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("/Notification/details");
		view.addObject("notification", notificationDao.find(id));
		
		return view;
	}
	

	@RequestMapping("/{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id) {
		ModelAndView view = new ModelAndView();
		view.addObject("notification", notificationDao.find(id));
		view.setViewName("Notification/edit");
		return view;
	}

	@RequestMapping("/add.html")
	public ModelAndView add() {
		ModelAndView view = new ModelAndView();
		view.addObject("notification", new Notification());
		view.setViewName("Notification/edit");
		return view;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("notification") Notification notification, BindingResult result, SessionStatus status) {
		if (notification.getId() == null) {
			notificationDao.save(notification);
			status.setComplete();
		}
		else {
			System.out.println(notification.toString());
			notificationDao.update(notification);
			status.setComplete();
		}
		return list();
	}
}
