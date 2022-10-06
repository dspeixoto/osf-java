package com.product.apirest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/customers").permitAll()
		.antMatchers("/orders").permitAll()
		.antMatchers("/products").permitAll()
		.antMatchers("/staffs").permitAll()
		.antMatchers("/staffs").permitAll()
		.antMatchers("/stores").permitAll()
		.antMatchers("/stocks").permitAll()
		.antMatchers("/orders/status").authenticated();
		super.configure(http);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
	
}
