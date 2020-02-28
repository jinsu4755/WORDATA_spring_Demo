package com.example.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.example.security.domain.User;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	// 사용자가 입력한 로그인 아이디와 비밀번호 검사하는 클래스
	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//사용자 입력 로그인 아이디와 비밀번호 검사시 Spring Security 엔진에서 이 클래스의 authenticate가 자동 호출
		// 사용자가 입력한 로그인 아이디와 비밀번호가 해당 메소드의 파라미터로 전달
		String loginId = authentication.getName();
		String passwd = authentication.getCredentials().toString();
		return authenticate(loginId, passwd);
	}

	public Authentication authenticate(String loginId, String password) throws AuthenticationException {
		User user = userService.login(loginId, password);
		if (user == null)
			return null;
		//사용자가 입력한 아이디 비밀번호 검사후 실패하면 null 반환
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		String role = "";
		switch (user.getUserType()) {
		case "관리자":
			role = "ROLE_ADMIN";
			break;
		case "교수":
			role = "ROLE_PROFESSOR";
			break;
		case "학생":
			role = "ROLE_STUDENT";
			break;
		}
		/*User 테이블의 userType 필드의 값은 '관리자', '교수', '학생' 이다.
		 * userType 이 '관리자', '교수', '학생' 인 경우에
		 * spring security 권한을 'ROLE_ADMIN', 'ROLE_PROFESSOR', 'ROLE_STUDENT' 으로 설정한다*/
		grantedAuthorities.add(new SimpleGrantedAuthority(role));
		return new MyAuthenticaion(loginId, password, grantedAuthorities, user);
	}

	public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
		private static final long serialVersionUID = 1L;
		User user;

		public MyAuthenticaion(String loginId, String passwd, List<GrantedAuthority> grantedAuthorities, User user) {
			super(loginId, passwd, grantedAuthorities);
			this.user = user;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
