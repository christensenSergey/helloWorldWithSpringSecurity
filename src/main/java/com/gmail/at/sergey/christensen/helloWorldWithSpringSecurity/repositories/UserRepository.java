package com.gmail.at.sergey.christensen.helloworldwithspringsecurity.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gmail.at.sergey.christensen.helloworldwithspringsecurity.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByLogin(String login);

}
