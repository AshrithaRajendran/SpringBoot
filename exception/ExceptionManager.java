package com.jsp.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jso.springbootdemo.ReponseStructure;

@RestControllerAdvice 
public class ExceptionManager {
	@ExceptionHandler(value=UserNotFoundException.class)
	public ReponseStructure<String> userNotFound(UserNotFoundException u)
	{
		ReponseStructure<String> rs=new ReponseStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData(u.getMessage());
		rs.setMessage("resquest failed");
		return rs;
		
	}

	

}
