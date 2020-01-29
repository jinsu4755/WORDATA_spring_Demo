package com.wordata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Data")
public class DataController {
	
	@RequestMapping("file-up")
	public String fileUp() {
		return "Data/file-up";
	}
	
	@RequestMapping("result")
	public String result() {
		return "Data/result";
	}

}
