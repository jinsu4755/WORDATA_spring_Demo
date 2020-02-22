package com.wordata.test.controller;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordata.test.domain.Course;
import com.wordata.test.domain.Department;
import com.wordata.test.domain.Professor;
import com.wordata.test.domain.Registration;
import com.wordata.test.domain.Student;
import com.wordata.test.repository.DepartmentRepository;
import com.wordata.test.repository.ProfessorRepository;
import com.wordata.test.repository.StudentRepository;

@RestController
@RequestMapping("api")
public class APIController {
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	ProfessorRepository professorRepository;

	@RequestMapping("departments")
	public List<Department> departments() {
		return departmentRepository.findAll();
	}

	@RequestMapping("students")
	public List<Student> students() {
		// 전체 학생 목록 출력 메소드
		return studentRepository.findAll();
	}

	@RequestMapping("department/{id}/students")
	public List<Student> departmentStudents(@PathVariable("id") int id) {
		// 학과 id의 학생들 출력 메소드
		Department department = departmentRepository.findById(id).get();
		return department.getStudents();
	}

	@RequestMapping("department/{id}/professors")
	public List<Professor> departmentProfessors(@PathVariable("id") int id) {
		// 학과 id의 교수들 출력 메소드
		Department department = departmentRepository.findById(id).get();
		return department.getProfessors();
	}

	@RequestMapping("student/{id}/registrations")
	public List<Registration> studentRegistrations(@PathVariable("id") int id) {
		// 학과 id의 수강정보들을 출력하는 메소드
		Student student = studentRepository.findById(id).get();
		return student.getRegistrations();
	}

//	@RequestMapping("student/{id}/courses")
//	public List<Course> studentCourses(@PathVariable("id") int id) {
//		//id가 3인 학생의 수강 강좌 출력
//		Student student = studentRepository.findById(id).get();
//		List<Course> list = new ArrayList<Course>();
//		for (Registration r : student.getRegistrations())
//			list.add(r.getCourse());
//		return list;
//	}
	@RequestMapping("student/{id}/courses")
	public Stream<Course> studentCourses(@PathVariable("id") int id) {
		return studentRepository
				.findById(id).get()
				.getRegistrations()
				.stream()
				.map(s -> s.getCourse());
	}

}
