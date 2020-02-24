package com.wordata.test.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = { "student", "course" })
@EqualsAndHashCode(exclude = { "student", "course" })
@Entity
@Table(name = "register") //DB테이블 이름과 클래스이름이 맞지 않는경우 사용
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Integer grade; //해당 필드는 nullable 이므로 null이 가능한데 int는 null 불가능이므로 Integer class 타입으로 선언
	Date createDate;
	@ManyToOne
	@JoinColumn(name = "studentId")
	Student student;
	@ManyToOne
	@JoinColumn(name = "courseId")
	Course course;
}