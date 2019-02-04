package com.gmail.at.sergey.christensen.helloworldwithspringsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.entities.User;
import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.service.interfaces.UserService;

@Component
public class DataInit implements ApplicationRunner {
 
    @Autowired
    UserService userService;
 
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	User defaultUser = new User("admin", "admin");
    	userService.save(defaultUser);
	}
}
