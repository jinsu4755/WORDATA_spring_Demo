package com.example.page.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.page.domain.Employee;

public interface EmployeeService {
	
	public Page<Employee> getEmployeeList(Pageable pageable);

}
