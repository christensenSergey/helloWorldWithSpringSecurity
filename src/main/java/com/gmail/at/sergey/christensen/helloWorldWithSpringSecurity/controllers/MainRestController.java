package com.gmail.at.sergey.christensen.helloworldwithspringsecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.entities.User;
import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.service.interfaces.UserService;

@RestController
@RequestMapping(value = "/api")
public class MainRestController {
	
	@Autowired
	UserService userService;
	
	final String LOGIN_EXIST = "Login already exist";
	final String WEAK_PASSWORD = "Password is weak. Password should contain not less than 8 characters,"
			+ " at least 1 digit and 1 letter, can contain capital letters and \"@#$%^&+=\"";
    
	@PostMapping(value = "/checkPasswordStrength")
    public String checkPasswordStrength(String newPassword) {
    	return userService.checkPasswordWithRegexp(newPassword);
    }
    
    @PostMapping(value = "/changePassword")
    public void changePassword(String login, String newPassword) {
    	User user = userService.findByLogin(login);
    	user.setPassword(newPassword);
    	userService.save(user);
    }
    
    @PostMapping(value = "/registration")
    public String registerUser(String login, String newPassword) {
    	if(userService.findByLogin(login) != null) {
    		return LOGIN_EXIST;
    	}
    	if(userService.checkPasswordWithRegexp(newPassword).equals("noPatternMatch")) {
    		return WEAK_PASSWORD;
    	}
		User user = new User(login, newPassword);
		userService.save(user);
		return "Success";
    }

}
