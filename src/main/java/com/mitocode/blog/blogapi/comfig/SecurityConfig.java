package com.mitocode.blog.blogapi.comfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET,"/api/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
}
