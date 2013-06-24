package pe.edu.ucsp.oms.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.edu.ucsp.oms.domain.User;
import pe.edu.ucsp.oms.repository.UserDao;
import pe.edu.ucsp.oms.service.UserService;


/**
 * 
 * @author jneyra
 *
 */
@Controller
@RequestMapping("/User")
public class UserController {
	final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;

	public UserController() {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User handleGetUserById(@PathVariable("id") int id) {
		return userDao.find(id);
	}

	@RequestMapping(method = RequestMethod.GET, params="email")
	public @ResponseBody
	List<User> handleGetUserByEmail(@RequestParam(value = "email", required = true) String email) {
		return userDao.filterByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User handleAddUsesr(@RequestBody User user) {
		userDao.save(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> handleListAll() {
		return userDao.findAll();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody Boolean handleRemoveByIds(@RequestParam("id") String[] ids) {
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting users with ID: " + Arrays.deepToString(ids));
		}
		return true;
	}
}
