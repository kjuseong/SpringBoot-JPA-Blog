package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;


// 해당 jparepository는 user테이블이 관리하는 repositry고 이 user테이블의 primarykey는 integer야
// JpaRepository가 기본적인 crud를 다 들고있음 쩐당...
// DAO 
// 자동으로 bean등록이 된다.
// @Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer>{

}



// JPA Naming 쿼리
// 실제로 findByUsernameAndPassword 이러한 함수명은 없으나 이름을 이렇게하면
// SELECT * FROM user WHERE username = ?(첫번째 파라미터) AND password = ?(두번째 파라미터);
// User findByUsernameAndPassword(String username, String password);

//	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);