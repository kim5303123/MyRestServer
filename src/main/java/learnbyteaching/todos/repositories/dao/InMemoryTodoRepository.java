package learnbyteaching.todos.repositories.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import learnbyteaching.todos.repositories.vo.TodoItem;

@Repository("todoRepository")
public class InMemoryTodoRepository implements TodoRepository {
	private final List<TodoItem> todos = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

	public InMemoryTodoRepository() {
		todos.add(new TodoItem(counter.incrementAndGet(), "웹 개발 기초 공부하기", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "HTML/CSS 복습하기", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "JavaScript 기초 실습하기", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "Spring Framework 학습", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "RESTful API 구현 실습", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "Git 기본 명령어 익히기", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "웹 보안 기초 이해하기", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "프론트엔드 프레임워크 공부", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "백엔드 데이터베이스 연동 학습", false));
        todos.add(new TodoItem(counter.incrementAndGet(), "프로젝트 아이디어 고민하기", false));
	}
	
	//	GET 메서드용
    @Override
    public List<TodoItem> findAll() {
        return new ArrayList<>(todos);
    }

    //	GET 메서드용
    @Override
    public Optional<TodoItem> findById(Long id) {
        return todos.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();
    }

    //	POST, PUT 메서드 처리용
    @Override
    public TodoItem save(TodoItem todoItem) {
        if (todoItem.getId() == null) {
            todoItem.setId(counter.incrementAndGet());
            todos.add(todoItem);
        } else {
            TodoItem existingTodo = findById(todoItem.getId()).orElse(null);
            existingTodo.setTitle(todoItem.getTitle());
            existingTodo.setCompleted(todoItem.isCompleted());
        }
        return todoItem;
    }

    //	DELETE 메서드 처리용
    @Override
    public void deleteById(Long id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}
