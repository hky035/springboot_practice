package com.example.learnspringsecurity.todo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TodoResource {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final List<Todo> todoService = 
			List.of(new Todo("kim", "Learn Java"), new Todo("kim","Learn Spring"));
	
	
	
	@GetMapping("users/{username}/todos")
	public Todo retrieveTodo(@PathVariable("username") String username, @RequestBody Todo todo) {
		return todoService.get(0);
	}
	
	@PostMapping("users/{username}/todos")
	public void addTodo(@PathVariable("username") String username, @RequestBody Todo todo) {
		logger.info("Create {} for {}", todo, username);
	}
}


record Todo (String username, String description) {}
