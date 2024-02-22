package com.heo.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int sequence = 0;
	static {
		todos.add(new Todo(++sequence, "Kim", "Learn Aws", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++sequence, "Kim", "Learn devOps", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++sequence, "Kim", "Learn Full Stack", LocalDate.now().plusYears(3), false));
	}

	public List<Todo> findByUsername(String username) {

		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		
		return todos.stream()
				.filter(predicate).toList();
	}

	public void addTodo(Todo todo) {
		todo.setId(++sequence);
		todos.add(todo);
	}

	public void deleteTodo(int id) {
//		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(todo -> todo.getId() == id);
//		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		return todos.stream()
				.filter(todo -> todo.getId() == id)
				.findAny().get();
	}

	public void updateTodo(Todo todo) {
		deleteTodo(todo.getId());
		todos.add(todo);
	}

}
