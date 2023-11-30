package com.cos.blog.controller.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
	// httpStatus는 enum값   ex)200, 404 이러한 값들 
	HttpStatus status;
	T data;
}
