package com.wordata.test.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	/*
	 * 아래 구현은 Single-Valued Association 구현이다
	 * 엔터티 클래스들 사이의 1 대 다 관계를 구현할 때,
	 * 외래키를 포함하는 엔터티 클래스(예: Employee 클래스)에 @ManyToOne 어노테이션을 사용하여,
	 * 상대측 엔터티 객체 한 개를 구현하는 것이 기본이다. (예: Department department 멤버 변수)
	 */
	@ManyToOne
	@JoinColumn(name = "departmentId")
	Department department;
	
	@OneToOne(mappedBy = "employee")
	Address address;

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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}