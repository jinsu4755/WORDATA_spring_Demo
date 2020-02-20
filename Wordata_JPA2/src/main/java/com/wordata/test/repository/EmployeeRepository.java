package com.wordata.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wordata.test.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
