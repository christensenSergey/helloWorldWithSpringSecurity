package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.entities.User;
import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.service.interfaces.UserService;

@Controller
@ResponseBody
@RequestMapping(value = "/api")
public class RestController {
	
	@Autowired
	UserService userService;
	
    @PostMapping(value = "/checkPasswordStrength")
    public String checkPasswordStrength(@RequestParam("newPassword") String newPassword) {
    	return userService.checkPasswordWithRegexp(newPassword);
    }
    
    @PostMapping(value = "/changePassword")
    public void changePassword(@RequestParam("login") String login, @RequestParam("newPassword") String newPassword) {
    	User user = userService.findByLogin(login);
    	user.setPassword(newPassword);
    	userService.save(user);
    }
    
    @PostMapping(value = "/registration")
    public String registerUser(@RequestParam("login") String login, @RequestParam("newPassword") String newPassword) {
    	if(userService.findByLogin(login) != null) {
    		return "Login already exist";
    	}
    	if(userService.checkPasswordWithRegexp(newPassword).equals("noPatternMatch")) {
    		return "Password is weak. Password should contain not less than 8 characters, at least 1 digit and 1 letter, can contain capital letters and \"@#$%^&+=\"";
    	}
		User user = new User(login, newPassword);
		userService.save(user);
		return "Success";
    }

}
