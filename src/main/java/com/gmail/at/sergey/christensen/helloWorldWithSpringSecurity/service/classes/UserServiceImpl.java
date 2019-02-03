package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.service.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.entities.User;
import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.repositories.UserRepository;
import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.service.interfaces.UserService;

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
		String weakPattern = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}";
    	String normalPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
    	String strongPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    	if(password.matches(strongPattern)) {
    		return "strong";
    	}
    	if(password.matches(normalPattern)) {
    		return "normal";
    	}
    	if(password.matches(weakPattern)) {
    		return "weak";
    	}
        return "noPatternMatch";
	}

}
