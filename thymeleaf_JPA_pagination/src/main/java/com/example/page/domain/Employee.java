package com.example.page.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "employee") // 테이블 이름 메칭
@Getter //getter 만 구현
@ToString
@EqualsAndHashCode(of= {"id", "name"}) // 명시적으로 해당 필드 포함
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자 생성시 protected 권한
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="departmentId")
	Department department;
	
	@Builder
	public Employee(String name, Department department) {
		this.name = name;
		this.department = department;
	}

}
