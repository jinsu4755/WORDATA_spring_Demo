package com.wordata.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wordata.test.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
