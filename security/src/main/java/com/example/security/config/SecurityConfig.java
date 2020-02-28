package com.example.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.security.service.MyAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyAuthenticationProvider myAuthenticationProvider;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").access("ROLE_ADMIN")
			// admin/** 패턴 url은 admin 권한만 요청 가능. 권한이 없으면 spring security엔진에 의해 거부
			.antMatchers("/professor/**").access("ROLE_PROFESSOR")
			.antMatchers("/guest/**").permitAll()
			// guest/** 패턴의 url은 모든 사용자에게 허용, 로그인 하지 않아도 볼 수 있는 public page
			.antMatchers("/").permitAll()
			.antMatchers("/**").authenticated();
			// /**의 url 패턴은 로그인 한 유저만 요청이 가능하다.
			/* '/' url은 '/**' url에 포함된다.
			 * 그래서 .antMatchers("/").permitAll() 이 설정이 없으면 '/'도 로그인된 사용자만 접근 가능
			 * 만약 순서가 바뀌어도 동일하다. 먼저 '/**'가 권한이 설정되면 후에 '/'에 모두 허용해도 이전 설정된 권한은
			 * 바뀌지 않는다고 한다.*/
		http.csrf().disable();
			// csrf 공격 검사를 잠시 하지 않음 예제 간단 구현을 위함
		
		http.formLogin() //로그인 페이지 설정 시작
			.loginPage("/guest/login") //로그인 페이지 url 설정
			.loginProcessingUrl("/guest/login_processing")
			//로그인 페이지에서 로그인 버튼을 눌러 요청할 url을 설정한다.
			/* 해당 url이 요청되면 로그인 검사를 수행
			 * spring security-> MyAuthenficationProvider의 authenticate 메소드 호출*/
			.failureUrl("/guest/login?error")
			//로그인 실패시 해당  url redirect
			.defaultSuccessUrl("/user/index", true)
			// 로그인이 성공하면 해당 url redirect
			.usernameParameter("loginId")
			.passwordParameter("passwd");
			//로그인 페이지 뷰 파일에서 아이디 input 태그 name 값과 비밀번호 input 태그 name 값 설정
		
		http.logout() //로그아웃 설정
			.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout_processing"))
			// 로그아웃 버튼이나 링크로 url요청하고 절차는 security 엔진에서 자동 진행
			.logoutSuccessUrl("/guest/login").invalidateHttpSession(true);
			// 로그 아웃후 넘어갈 redirect url 지정하며 세션에 들어있는 데이터는 모두 삭제
		
		http.authenticationProvider(myAuthenticationProvider);
		
		/* 로그인 하지 않은 사용자만 로그아웃url을 요청하기에
		 * 로그인 페이지는 guest/login이고
		 * 로그인 처리 url은 /guest/login_processing 이다.*/
	}
}
