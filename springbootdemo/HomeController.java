package com.jso.springbootdemo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@PostMapping("/hi")
	public String m1()
	{
		return "sprinboot-first program";
	}

}
