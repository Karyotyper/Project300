package com.great.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
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
			@RequestParam("password") String password){
		
		
		User user =new User();
		
		user=userService.getUserByName(username);
		
		if(user.getPassword().equals(password)){
			
		
		
		System.out.println("Username: "+username);
		System.out.println("Password: "+password);
		return "tasks";}
		
		else return "login";
		
	}
	
}
