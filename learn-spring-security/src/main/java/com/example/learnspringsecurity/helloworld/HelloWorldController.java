package com.example.learnspringsecurity.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/hello-world")
	@CrossOrigin(origins="http://www.in28minutes.com")
	public String helloWorld() {
		return "hello";
	}
	
}
