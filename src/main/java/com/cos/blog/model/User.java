package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM - > Java(다른언어) Object -> 테이블로 매핑해주는 기술 
// 스키마 변경하면 yml에서 create로 바꿔야 되요

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // oracle : 시퀀스 , mySQL : auto_increment
	
	@Column(nullable =  false, length = 30, unique = true) // null이 될수없고 30자리
	private String username; // 아이디
	
	@Column(nullable = false, length = 100) // 123456 = > 해쉬(비밀번호 암호화)를 데이터베이스에 넣을거임
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	
	
	//@ColumnDefault("user")
	// DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING) // DB는 ENUM타입이 없으므로 String 타입이라고 알려주는 어노테이션
	private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager  
	
	  
	@CreationTimestamp // 시간이 자동 입력 // 자바에서 시간을 넣어줌 
	private Timestamp createDate;
	
}
