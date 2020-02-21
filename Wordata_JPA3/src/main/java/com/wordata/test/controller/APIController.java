package com.wordata.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordata.test.domain.Department;
import com.wordata.test.domain.Employee;
import com.wordata.test.repository.DepartmentRepository;
import com.wordata.test.repository.EmployeeRepository;

@RestController
@RequestMapping("api")
public class APIController {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping("employees")
	public List<Employee> employees() {
		return employeeRepository.findAll();
	}

	@RequestMapping("employee/{id}")
	public Employee employee(@PathVariable("id") int id) {
		return employeeRepository.findById(id).get();
	}

	@RequestMapping("departments")
	public List<Department> departments() {
		return departmentRepository.findAll();
	}

	@RequestMapping("department/{id}/employees")
	public List<Employee> departmentEmployees(@PathVariable("id") int id) {
		Department department = departmentRepository.findById(id).get();
		return department.getEmployees();
	}
}