package com.example.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.page.domain.Department;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
