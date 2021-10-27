package com.metehan.app.ws.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.metehan.app.ws.service.SellerService;
import com.metehan.app.ws.service.UsersService;
@Configuration
@EnableWebSecurity
public class SellerWebSecurity extends WebSecurityConfigurerAdapter {
	
	private Environment environment;
	private SellerService sellerService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public SellerWebSecurity(SellerService sellerService, BCryptPasswordEncoder bCryptPasswordEncoder, Environment environment )
	{
		this.sellerService = sellerService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.environment = environment;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/sellers/**").permitAll()
		.and()
		.addFilter(getAuthenticationFilter());
		http.headers().frameOptions().disable();
	}
	
	private SellerAuthenticationFilter getAuthenticationFilter() throws Exception{
		
		SellerAuthenticationFilter authenticationFilter = new SellerAuthenticationFilter(sellerService, environment, authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("seller.login.url.path"));
		return authenticationFilter; 
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(sellerService).passwordEncoder(bCryptPasswordEncoder);
	}

	
	
	
	

}