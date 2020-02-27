package com.example.page.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "department") // 테이블 매칭
@Getter //getter만 생성
@ToString
@EqualsAndHashCode(of = {"id","name"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@Builder
	public Department(String name) {
		this.name = name;
	}

}
