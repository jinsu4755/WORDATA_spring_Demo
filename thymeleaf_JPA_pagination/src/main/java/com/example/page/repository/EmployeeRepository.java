package com.example.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.page.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
