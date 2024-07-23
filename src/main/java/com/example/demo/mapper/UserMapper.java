package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.example.demo.domain.Pagination;
import com.example.demo.domain.User;

@Mapper
public interface UserMapper {
	
	public User readUser(String username);
	
	public void createUser(User user);
	
	public List<GrantedAuthority> readAuthorities(String username); //granted 부여
	
	public void createAuthority(User user);
	
	
	public int getUsersCount(Pagination pagination);
	public List<User> getUsers(Pagination pagination);
}
