package com.heo.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {

	private TodoRepository repository;

	@Autowired
	public TodoControllerJPA(TodoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@RequestMapping("list-todos")
	public String todolist(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = repository.findByUsername(username);
		model.addAttribute("todos",todos); // 우선은 하드코딩
	
		return "listTodos";
	}
	
	private String getLoggedInUsername(ModelMap model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return authentication.getName();
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String gotoAddTodoPage(ModelMap model) {	
		String username = getLoggedInUsername(model); // loginController에서 모델에 담긴 name이 세션에 저장
		// todos.jsp에서 model.Attribute="todo"로 model에 todo키를 가진 Todo 객체가 담겨있어야야 사용 가능함
		Todo todo = new Todo(0,username," ",LocalDate.now(),false);
		model.put("todo", todo);
		return "todos";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) { 
		// 커맨드 빈 사용해서 form에서 제출된 값들이 todo의 속성에 바인딩 되어서 사용됨
		// 새로운 todo 등록 시 길이 제한 두기로 했었다. 어떻게? @Valid와 BindingResult 사용
		
		if(result.hasErrors()) {

			return "todos"; // todos.jsp에 form:errors로 에러 띄우기
		}
			// 에러메시지 추가 필요
	
		todo.setUsername((String)model.get("name"));
		// 검증 절차 필요
		repository.save(todo); // addTodo에서 id 설정
		
		return "redirect:list-todos"; // 리다이렉션을 통해 모델로 투두를 추가하는 코드 굳이 작성 불필요
	}
		
	// 어차피 delete는 요청 하나만 보내니 get,post 구분 없이 그냥 모든 요청에 대해 처리하도록
	@RequestMapping(value="delete-todo")
	public String deleteTodo(@RequestParam int id) {
		// delete시 jsp에서 todo의 id를 전달함. 단지 id만 전달받아서 deleteTodo(id)메서드만 실행시키면 됨
		// listTodos.jsp에서 href="delete-todo?id=${todo.id}" 잊지말기
		repository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String gotoUpdateTodoPage(@RequestParam int id, ModelMap model) {
		// GET 요청에서 기억해야할건, 어떤 엔티티를 수정할 것인가? id 기억
		// 해당 id에 해당하는 Todo를 모델에 담아 전달
		
		Todo targetTodo = repository.findById(id).get(); // Optional 반환
		model.put("todo", targetTodo);  // 현재 문제가 다른 사람이 만든 것 까지 업데이트 및 삭제 가능
//		model.addAttribute("todo",todo);
		return "todos";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) { // 실패의 경우 
			return "todos";
		}
		// 성공시
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		repository.save(todo); // save 해도 기존 요소있으면 업데이트 됨
		return "redirect:list-todos";
	}
	
	
	

}
