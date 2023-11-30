package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 - > 응답(HTML 파일)
// @Controller

// 사용자가 요청 ->  거기에 대한 응답(Data) 
// @ RestController

@RestController
public class HttpControllerTest {

		private static final String TAG = "HttpControllerTest: ";
		
		@GetMapping("/http/lombok")
		public String lombokTest() {
			// 빌더를 쓰면 순서를 안지켜도됨 생성자를 자유자제로 사용가능
			Member m = Member.builder().username("kim").password("1234").email("sjkh972@naver.com").build();
			System.out.println(TAG + "getter: " + m.getUsername());
			m.setUsername("cos");
			System.out.println(TAG + "setter: " + m.getUsername());
			return "lombok Test 완료";
		}
		//인터넷 브라우저 요청은 get 요청밖에 할수 없다.
	
		//http://localhost:9000/http/get (select)
//		// 하나하나 받기
//		@GetMapping("/http/get")
//		public String getTest(@RequestParam int id, @RequestParam String username) {
//			return "get 요청 : "+ id + "," + username;
//		}
		
		@GetMapping("/http/get") // id=1&username=kim&password=1234&email=ssar@nate.com
		public String getTest(Member m) {

			return "get 요청 : "+ m.getId() + "," + m.getUsername()+ "," + m.getPassword() + "," + m.getEmail();
		}
		
		//http://localhost:9000/blog/http/post (insert)
		// postman x-www-form-urlencoded < - form 태그
		// post 데이터는 body에 포함되서 전송되므로 @requestbody로 받아야 함
		@PostMapping("/http/post") //text/plain, application/json
		public String postTest(@RequestBody Member m) { //MessageConverter (스프링 부트)
			return "post 요청 : "+ m.getId() + "," + m.getUsername()+ "," + m.getPassword() + "," + m.getEmail();
		}
		
		//http://localhost:9000/http/put (update)
		// body에 데이터를 싣어서 보내면 @requestbody매핑을 통해서 오브젝트(Member)로 매핑해서 받을수있음
		@PutMapping("/http/put")
		public String putTest(@RequestBody Member m) {
			return "put 요청 : "+ m.getId() + "," + m.getUsername()+ "," + m.getPassword() + "," + m.getEmail();
		}
		@DeleteMapping("/http/delete")
		//http://localhost:9000/http/delete (delete)
		public String deleteTest() {
			return "delete요청";
		}
		
		
	
}
