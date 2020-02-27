package com.example.page.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.page.domain.Employee;
import com.example.page.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired private EmployeeRepository EmployeeRepository;
	// Repository를 자동으로 구현해줌
	
	@Override
	public Page<Employee> getEmployeeList(Pageable pageable) {
		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        // page default 값이 0 이다. 하지만 개시판에서는 1이 첫 페이지 이므로 index를 하나 줄여준 것
		// pageable = PageRequest.of(page, 10); 해당 코드는 sort하지 않음
		pageable = PageRequest.of(page, 10, Sort.by("id").descending());
		//descending(내림차순) 정렬, ascending(오름차순) 정렬
		// and()로 조건을 더 붙일 수 있음
		/* Pageable 인터페이스를 확인하면 getter만 존재한다.
		 * PageRequest.of 메소드를 사용하여 새로운 pageable 객체를 생성하여 setter 대체?*/
		
        return EmployeeRepository.findAll(pageable);
        
	}

}
