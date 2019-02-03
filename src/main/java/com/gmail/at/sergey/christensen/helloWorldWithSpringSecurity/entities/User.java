package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 4728284178941077619L;

	public User() {
		super();
	}
	
	public User(@NotBlank(message = "Login may not be blank") String login,
			@NotBlank(message = "Password may not be blank") String password) {
		super();
		this.login = login;
		this.password = password;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@Column(name = "login", unique = true)
    @NotBlank(message = "Login may not be blank")
    private String login;

    @Column(name = "password")
    @NotBlank(message = "Password may not be blank")
    private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
