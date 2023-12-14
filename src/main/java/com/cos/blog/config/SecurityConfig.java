package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // 빈등록 (IoC관리)
@ EnableWebSecurity //시큐리티 필터가 등록이된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
//모든 리퀘스트요청 컨트롤러로가서 함수가 실행되기전에 SecurityConfig로 온다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() // request가 들어오면 
				.antMatchers("/auth/**") // auth경로로 들어오는건
				.permitAll()  //누구나 들어올수 있다.
				.anyRequest() // 다른요청들은
				.authenticated() // 인증이 되야한다. 
			.and()
				.formLogin()
				.loginPage("/auth/loginForm"); // 인증이 필요한곳으로 요청이 오면 /auth/loginForm으로 이동
	}
}
