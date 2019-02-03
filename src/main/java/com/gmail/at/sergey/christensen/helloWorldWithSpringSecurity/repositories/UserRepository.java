package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByLogin(String login);

}
