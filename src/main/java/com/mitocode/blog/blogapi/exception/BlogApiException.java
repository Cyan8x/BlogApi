package com.mitocode.blog.blogapi.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	
	
}
