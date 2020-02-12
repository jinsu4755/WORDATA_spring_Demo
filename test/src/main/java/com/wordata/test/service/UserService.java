package com.wordata.test.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wordata.test.domain.User;

public interface UserService extends UserDetailsService {
	Collection<GrantedAuthority> getAuthorities(String username); //user 권한을 읽는 메소드
	public User readUser(String username);
	public void createUser(User user);
	public void deleteUser(String username);
	public PasswordEncoder passwordEncoder();
}
