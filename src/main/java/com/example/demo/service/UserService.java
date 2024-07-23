package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.domain.Pagination;
import com.example.demo.domain.User;

public interface UserService extends UserDetailsService{
	
	public User readUser(String username);
	
	public void createUser(User user);
	
	public void createAuthorities(User user);
	
	Collection<GrantedAuthority> getAuthorities(String username); //시큐리티 권한 얻기

	
	
	public int getUsersCount(Pagination pagination);
	public List<User> getUsers(Pagination pagination);
}
