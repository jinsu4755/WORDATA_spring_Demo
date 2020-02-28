package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestController {
	@RequestMapping({ "/", "guest/index" })
	//두 url 모두 동일한 처리
	public String index() {
		return "guest/index";
	}

	@RequestMapping("guest/login")
	public String login() {
		return "guest/login";
	}
}
