package learnbyteaching.todos.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import learnbyteaching.todos.repositories.dao.TodoRepository;
import learnbyteaching.todos.repositories.vo.TodoItem;

@RequestMapping("/api/todos")
@RestController	// @Controller + @ResponseBody
public class TodoApiController {
	//	Repository 연결
	@Autowired
	private TodoRepository todoRepository; 

	//	객체를 단순히 리턴만 하면 status 200번만 전송
	//	보다 세밀한 응답 정보를 함께 보내고자 한다면 ResponseEntity를 사용
	@GetMapping
//	public List<TodoItem> getAllTodos() {
	public ResponseEntity<List<TodoItem>> getAllTodos() {
		List<TodoItem> todos = todoRepository.findAll();
		return ResponseEntity.ok(todos);
		//	status 200에 body에 todos 실어보냄
	}
	
	//	id로 TODO 항목 조회 -> /api/todos/{id}
	@GetMapping("/{id}")
	public ResponseEntity<TodoItem> getTodoById(
			@PathVariable("id") Long id) {
		Optional<TodoItem> todo = todoRepository.findById(id);
		return todo.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	//	keyword 파라미터 전달 받아서 title을 대상으로 검색 -> 목록
	@GetMapping("/search")
	public ResponseEntity<List<TodoItem>> searchTodos(
			@RequestParam("keyword") String keyword) {
		List<TodoItem> foundTodos = 
			todoRepository.findAll().stream()
				.filter(todo -> todo.getTitle()
									.toLowerCase()
									.contains(keyword.toLowerCase()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(foundTodos);
	}
	
	//	새로운 TodoItem 생성
	@PostMapping
	public ResponseEntity<TodoItem> createTodo(
			@RequestBody TodoItem todoItem
			) {
		TodoItem savedTodo = todoRepository.save(todoItem);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.header("Location", "/api/todos/" + savedTodo.getId())
				.body(savedTodo);
		//	TODO: 나중에 수정
	}
}
