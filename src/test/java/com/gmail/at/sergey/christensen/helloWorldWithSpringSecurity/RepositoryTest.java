package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.entities.User;
import com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.repositories.UserRepository;

@Transactional
public class RepositoryTest extends HelloWorldWithSpringSecurityApplicationTests{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void constraintCheck() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = new User(" ", " ");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
        
        assertEquals(2, constraintViolations.size());
    }

    @Test
    public void invalidLogin() {
    	User user = userRepository.findByLogin("doesNotExist");
    	
    	assertThat(user, nullValue());
    }
    
    @Test
    public void adminAdded() {
        User user = userRepository.findByLogin("admin");
        
        assertThat(user, notNullValue());
        assertThat(user.getId(), is(1L));
        assertThat(user.getLogin(), is("admin"));
    }
    
    @Test
    public void saveUser() {
        User user = new User("login", "password");
        userRepository.save(user);
        user = null;
        user = userRepository.findByLogin("login");

        assertThat(user, notNullValue());
        assertThat(user.getId(), is(2L));
        assertThat(user.getLogin(), is("login"));
    }
    
}

