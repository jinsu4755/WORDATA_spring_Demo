package com.wordata.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wordata.test.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> { }
