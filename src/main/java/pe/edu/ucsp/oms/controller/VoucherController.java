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

import pe.edu.ucsp.oms.domain.Voucher;
import pe.edu.ucsp.oms.repository.VoucherDao;

@Controller
@RequestMapping("/Voucher")
public class VoucherController {

	@Inject
	VoucherDao voucherDao;

	@RequestMapping("/list.html")
	public ModelAndView list(){
		return new ModelAndView("Voucher/list" , "vouchers" , voucherDao.findAll());
	}
	
	@RequestMapping("{id}/details")
	public ModelAndView details (@PathVariable Long id){
		ModelAndView view = new ModelAndView();
		view.setViewName("Voucher/details");
		view.addObject("voucher" , voucherDao.find(id));
		return view;
	}
	
	@RequestMapping("{id}/edit.html")
	public ModelAndView edit(@PathVariable Long id){
		ModelAndView view =  new ModelAndView();
		view.setViewName("Voucher/edit");
		view.addObject("voucher" , voucherDao.find(id));
		return view;
	}
	
	@RequestMapping("/add.html")
	public ModelAndView add(){
		ModelAndView view = new ModelAndView();
		view.setViewName("Voucher/edit");
		view.addObject("voucher", new Voucher());
		return view;
	}
	
	@RequestMapping(value = "/save.html" , method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("voucher") Voucher voucher, BindingResult result, SessionStatus status){
		if(voucher.getId() == null){
			System.out.println("Esta creando un nuevo voucher");
			System.out.println(voucher.getPoints() + " " +voucher.getDiscount());
			voucherDao.save(voucher);
			status.setComplete();
		}
		else{
			voucherDao.update(voucher);
			status.setComplete();
		}
		return list();
	}
}
