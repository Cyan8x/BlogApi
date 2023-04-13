package com.mitocode.blog.blogapi.Security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mitocode.blog.blogapi.entity.Role;
import com.mitocode.blog.blogapi.entity.User;
import com.mitocode.blog.blogapi.repository.UserRepository;

@Service
public class CustomUserDetalsService implements UserDetailsService{

	UserRepository userRepository;
	
	
	public CustomUserDetalsService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()-> new UsernameNotFoundException("User not found by username o email: " + usernameOrEmail));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail()
				,user.getPassword()
				,mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
		return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
