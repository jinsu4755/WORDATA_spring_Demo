package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.domain.User;
import com.example.security.service.UserService;

@Controller
public class GuestController {
	
	@Autowired UserService userService;
	
	@RequestMapping({ "/", "guest/index" })
	//두 url 모두 동일한 처리
	public String index() {
		return "guest/index";
	}

	@RequestMapping("guest/login")
	public String login() {
		return "guest/login";
	}
	
	@GetMapping("guest/signup")
	public String signup() {
		return "guest/signup";
	}
	
	@PostMapping("guest/signup")
	public String signup(User user) {
		userService.signup(user);
		
		return "redirect:/guest/login";
	}
}
