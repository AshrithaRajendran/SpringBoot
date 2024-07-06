package com.jsp.springbootdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.jso.springbootdemo.ReponseStructure;

@RestController
public class ExceptionsHandler {
	@ExceptionHandler(value=ProductNotFoundException.class)
	public ReponseStructure<ProductNotFoundException> exception1(ProductNotFoundException pe)
	{
		ReponseStructure<ProductNotFoundException> rs=new ReponseStructure<>();
		
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setData(pe);
		rs.setMessage(pe.getMessage());
		return rs;
		
	}

}
