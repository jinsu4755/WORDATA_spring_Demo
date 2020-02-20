package com.wordata.test.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	
	/*
	 * 아래 구현은 Collection-Valued Association 구현이다
	 * 엔티티 클래스들 사이에 일대 다 관계를 구현시,
	 * 외래키를 포함하지 않는 엔터티 클래스에 상황에 맞는 어노테이션으로
	 * 해당 클래스로 생성된 객체에서 연관된 테이블 정보를 구할 때 사용
	 */
	@JsonIgnore //현재 RestController를 사용하여 JSON객체 변환을 하므로 , Infinite recursion 방지를 위해 사용
	@OneToMany(mappedBy="department") 
	List<Employee> employees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}