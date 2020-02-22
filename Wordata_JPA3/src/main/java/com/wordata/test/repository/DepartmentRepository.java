package com.wordata.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wordata.test.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> { }
