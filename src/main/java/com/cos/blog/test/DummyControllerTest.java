package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 restController
@RestController
public class DummyControllerTest {
	
	@Autowired // DummyControllerTest가 메모리 뜰때 userRepository도 같이뜸 // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String  delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		
			// Exception을 그냥 써도 됨 
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제 되었습니다. id:" + id;
	}
	
	
	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 id에 대한 데이터가 있으면 update를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요
	// email, password
	@Transactional // 함수 종료시에 자동 commit이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json데이터를 요청 => Java Object(MessageConverter의 Jacksjon라이브러리가 변환해서 받아준다.)
		System.out.println("id:" + id);
		System.out.println("password:" + requestUser.getPassword());
		System.out.println("email:" + requestUser.getEmail());
		
		// db에서  id를 넣어서 영속성컨텍스트로 가져옴 영속화 되서 1차캐시에 저장됨 
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		
		// 이 과정으로 set을 해서 값을 변경이 되기 때문에 DB가 수정이 됨
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//Transactional을 쓰면 save를 사용하지 않아도 update됨
		//userRepository.save(user);
		
		//더티 체킹
		
		
		return user;
	}
	
	
	
	// http://localhost:9000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();	
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	// http://localhost:9000/blog/dummy/user/page
	@GetMapping("/dummy/user")
	// 2건식 id를 기준으로 내림차순
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
		Page<User> pagingUsers =  userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	
	//{id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:9000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오개 되면 user가 uull이 될것 아냐?
		// 그럼 return null 이 리턴이 되자나 .. 그럼 프로그램에 문제가 있을수 있음
		// Optional로 너의 User가 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return
		
		// 람다식
		// ()-> 빈관호를  함으로서 orElseThrow()안의 모든것을 사용가능
//		User user1 = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당사용자는 없습니다,");
//		});
		
		User user =  userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. Id: " + id);
			}
		});
		// 요청 : 웹 브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할 수 있는 데이터) - > json(Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 자동
		// 만약에 자바 오프젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던짐
		// 그래서 웹에서 확인하면 {"id":0,"username":null,"password":null,"email":null,"role":null,"createDate":null}
		// 위 처럼 json오브젝트처럼 보임
		return user;
	}
	
	
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key =value(약속된 규칙)
		System.out.println("id:" + user.getId());
		System.out.println("username:" + user.getUsername());
		System.out.println("password:" + user.getPassword());
		System.out.println("email:" + user.getPassword());
		System.out.println("role:" + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
}
