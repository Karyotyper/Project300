package com.great.cms.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.great.cms.db.entity.User;
import com.great.cms.db.entity.UserType;
import com.great.cms.service.UserService;

@Controller
@SessionAttributes("UserRole")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/checklogin", method = RequestMethod.POST)
	public String checkLogin(@RequestParam("user_name") String username,
			@RequestParam("password") String password, Model model,
			HttpServletRequest request) {

		System.out.println("Username and Password: " + username + " "
				+ password);
		User user = null;

		user = userService.getUserByName(username);
		System.out.println("loginController");
		if (user == null) {
			model.addAttribute("message", "Invalid usrname or password");
			return "login";
		}

		else if (user.getPassword().equals(password)) {
			
            UserType Type= user.getUserTypeId();
            
			System.out.println("User Id: " + user.getUserId());
			model.addAttribute("userId", user.getUserId());
			model.addAttribute("username", username);
			model.addAttribute("UserRole" , user);
			
			System.out.println("Password: " + password);
			String Role=user.getUserTypeId().getUserTypeName();
			System.out.println("role now "+Role);
			if(Role.equals("Teacher")){
				System.out.println("Going to teacher course");
				return "course";
			}
			else
			return "stdcourse";
		}

		else {
			System.out.println("Not a successful login");
			model.addAttribute("message", "Invalid usrname or password");
			return "login";
		}

	}
	
	@RequestMapping(value = "/sign-in.html", method = RequestMethod.GET)
	public String logOut( Model model) {
		
		return "login";
		
		
		
	
	}

}