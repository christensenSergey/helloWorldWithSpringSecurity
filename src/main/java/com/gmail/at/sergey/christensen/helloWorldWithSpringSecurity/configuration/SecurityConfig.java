package com.gmail.at.sergey.christensen.helloworldwithspringsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
	}

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        	.csrf()
        		.disable()
        	.headers()
        		.frameOptions()
        		.disable()
        	.and()
        	.authorizeRequests()
        		.antMatchers("/webjars/**", "/login", "/registration",
        		"/api/registration", "/js/registration.js", "/js/login.js",
        		"/css/appStyle.css", "/accessDenied")
        		.permitAll()
        	.and()
        	.authorizeRequests()
        		.anyRequest()
        		.authenticated()
 	        .and()
 	        .formLogin()
 	        	.loginPage("/login")
 	        	.usernameParameter("login")
 	        	.passwordParameter("password")
 	        	.failureUrl("/login?error=error")
 	        	.defaultSuccessUrl("/", true)
			.and()
			.exceptionHandling()
				.accessDeniedPage("/accessDenied")
			.and()
			.sessionManagement()
				.maximumSessions(1); // avoiding multiple login
    }
}
