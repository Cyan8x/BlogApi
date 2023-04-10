package com.mitocode.blog.blogapi.exception;

import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mitocode.blog.blogapi.payload.ErrorDetailsDto;

@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{ 

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetailsDto> handleResorceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
		ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetailsDto>(errorDetailsDto,HttpStatus.NOT_FOUND);
	}
	
	//No funciona
	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetailsDto> handleResorceNotFoundException(BlogApiException exception, WebRequest webRequest){
		ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(new Date(), exception.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<ErrorDetailsDto>(errorDetailsDto,HttpStatus.BAD_REQUEST);
	}
	
	//No funciona
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,HttpHeaders headers,HttpStatus status,WebRequest request){
		Map<String,String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName =  ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
