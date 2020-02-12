package com.wordata.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordatestController {
	@RequestMapping("/")
	public String login() {
		return "hello";
	}
	
	@RequestMapping("/admin")
    public String admin() {
         return "This is admin page";
    }
   
    @RequestMapping("/user")
    public String user() {
         return "this is user page";
    }
}
