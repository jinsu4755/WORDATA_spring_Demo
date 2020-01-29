package com.wordata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wordata.model.Account;

@Controller
public class WordataController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/info")
	public String info() {
		return "info";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, @RequestParam("id")String id,
									@RequestParam("password")String password) {
		model.addAttribute("id", id);
		model.addAttribute("password", password);
		System.out.printf("id: %s pass: %s", id, password);
		return "login";
	}
	
	@RequestMapping(value="/account", method=RequestMethod.GET)
	public String account(Model model) {
		model.addAttribute("account", new Account());
		return "account";
	}
	
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String account(Model model, Account account) {
		System.out.println("id"+account.getId());
		System.out.println("password"+account.getPassword());
		System.out.println("name"+account.getName());
		return "account";
	}
}
