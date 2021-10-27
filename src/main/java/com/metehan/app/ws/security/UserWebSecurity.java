package com.metehan.app.ws.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.metehan.app.ws.service.UsersService;
@Configuration
@EnableWebSecurity
@Order(1)
public class UserWebSecurity extends WebSecurityConfigurerAdapter {
	
	private Environment environment;
	private UsersService usersService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserWebSecurity(UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment environment )
	{
		this.usersService = usersService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.environment = environment;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/users/**").permitAll()
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
	private UserAuthenticationFilter getAuthenticationFilter() throws Exception{
		
		UserAuthenticationFilter authenticationFilter = new UserAuthenticationFilter(usersService, environment, authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("user.login.url.path"));
		return authenticationFilter; 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
	}

	
	
	
	

}
