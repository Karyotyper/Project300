package com.great.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.great.cms.db.entity.User;
import com.great.cms.service.UserService;

@Controller

public class LoginController {
	
	@Autowired 
	private  UserService userService;
	

	
	@RequestMapping("/checklogin")
	public String checkLogin(@RequestParam("username") String username, 
			@RequestParam("password") String password ,Model model){
		
		
		User user =null;
		System.out.println("usr name = " + username + " pass : " + password);
		user=userService.getUserByName(username);
		if(user==null){
			model.addAttribute("message", "Invalid usrname or password");
			return "login";
		}
		
		if(user.getPassword().equals(password)){
			
		
		
		System.out.println("Username: "+username);
		System.out.println("Password: "+password);
		return "tasks";
		}
		
		else {
			model.addAttribute("message", "Invalid usrname or password");
			return "login";
		}
		
	}
	/**
	 * Created this method for future purpose, in case we include sign up functionality
	 * @param newUser
	 * @return
	 */
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String userSignUp(User newUser) {
		
		System.out.println("/signup");
		User user = null;
		  user = userService.getUserByName(user.getUserName());
		  
		if (user != null) {
			
			System.out.println("User account exists");
			
			return "signup";
		} 
		else {
			userService.save(newUser);
			System.out.println("User account created!");
			
			return "signup";
		}
	}
}