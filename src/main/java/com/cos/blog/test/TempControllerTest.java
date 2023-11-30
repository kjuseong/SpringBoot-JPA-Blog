package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller   // 그냥 컨트롤러는 파일을 리턴 함 
public class TempControllerTest {

	
	//http://localhost:9000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("temphome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 기본경로 이하에 있는 리턴(파일을) 해줌
		// 리턴명 : /home.html 이라 해야함
		// 풀 경로 : src/main/resoucres/static/home.html;
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";
	}
	
	
	// jsp는 동적인 파일 자바파일이라 컴파일이 일어나야 하는데
	// jsp를 리턴하면 브라우저가 인식을 못함 
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix : /WEB-INF/views/
		// suffix : jsp
		// 풀네임 : /WEB-INF/views/test.jsp
		return "test";
	}
	
}
