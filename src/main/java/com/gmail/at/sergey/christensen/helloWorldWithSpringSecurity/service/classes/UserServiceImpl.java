package com.gmail.at.sergey.christensen.helloworldwithspringsecurity.service.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.entities.User;
import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.repositories.UserRepository;
import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public String checkPasswordWithRegexp(String password) {
		final String WEAK_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}";
		final String NORMAL_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
		final String STRONG_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

		if(password.matches(STRONG_PATTERN)) {
    		return "strong";
    	}
    	if(password.matches(NORMAL_PATTERN)) {
    		return "normal";
    	}
    	if(password.matches(WEAK_PATTERN)) {
    		return "weak";
    	}
        return "noPatternMatch";
	}
}
