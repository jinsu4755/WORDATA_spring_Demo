package com.example.page.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.page.domain.Employee;
import com.example.page.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로깅 기능 사용을 위한 어노테이션
@Controller
public class EmployeeController {
	
	@Autowired EmployeeService employeeService;
	
	@GetMapping("/employee") //@RequestMapping(method = RequestMethod.GET)
	public String employeeView(@PageableDefault Pageable pageable, Model model) {
		/* Pageable은 Spring에서 페이징 기능을 위한 파라미터들을 추상화 시킨 인터페이스 
		 * 내부적으로 size는 10, page는 0이 기본 값으로 설정되어 있음*/
		Page<Employee> employeeList = employeeService.getEmployeeList(pageable);
		model.addAttribute("employeeList", employeeList);
		
		log.info("Total Elements : {}, Total pages : {}, 페이지에 표시할 element 수 : {},"
				+ " 현재 페이지 index : {}, 현재 페이지의 element 수 : {}", 
				employeeList.getTotalElements(),
				employeeList.getTotalPages(),
				employeeList.getSize(),
				employeeList.getNumber(),
				employeeList.getNumberOfElements());
		/* Page로 해당 페이지 정보를 메소드로 가져오기에 해당 정보를 볼 로그 생성*/
		
		return "employee";
		/* /employee를 호출하면 employee라는 view를 불러온다.
		 * spring boot 자동 설정으로 resource 폴더 templates/employee를 불러온다*/
	}
	

}
