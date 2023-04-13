package com.mitocode.blog.blogapi.comfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET,"/api/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		return http.build();
	}
	
}
