package com.example.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findOneByLoginId(String loginId);
	/* 만약 DB에 loginId로 조회한 레코드가 2개 이상일 경우 에러 발생
	 * loginId 값이 중복되는 레코드가 user 테이블에 저장되지 않도록 하기 위해서는
	 * 해당 필드에 unique index를 생성한다.*/

}
