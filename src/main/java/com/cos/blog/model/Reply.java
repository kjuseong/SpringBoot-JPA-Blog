package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Reply {
	
	@Id // Primary key
	@GeneratedValue(strategy =  GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // oracle : 시퀀스 , mySQL : auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne // Many  = 댓글, one = 보드 하나의 게시글에는 여러개의 답변
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne // 여러개의 답변을 하나의 유저가 쓸수있다 앞에 many가 해당 클래스
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
