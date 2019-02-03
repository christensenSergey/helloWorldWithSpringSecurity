package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.service.interfaces;

import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.entities.User;

public interface UserService {

		public User findByLogin(String login);
		
		public void save(User user);

		public String checkPasswordWithRegexp(String password);

}
