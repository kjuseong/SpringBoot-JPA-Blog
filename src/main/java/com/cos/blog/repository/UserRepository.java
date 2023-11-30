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
