package com.gmail.at.sergey.christensen.helloWorldWithSpringSecurity.configuration;

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
        httpSecurity.authorizeRequests().antMatchers("/webjars/**", "/login", "/registration",
        		"/api/registration", "/js/registration.js", "/accessDenied").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();
 
        httpSecurity.formLogin()
		.loginPage("/login").usernameParameter("login").passwordParameter("password")
		.failureUrl("/login?error=error").defaultSuccessUrl("/", true).and()
		.sessionManagement().maximumSessions(1); // avoiding multiple login

        httpSecurity.exceptionHandling().accessDeniedPage("/accessDenied");
		
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
    
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
