package learnbyteaching.todos.repositories.dao;

import java.util.List;
import java.util.Optional;

import learnbyteaching.todos.repositories.vo.TodoItem;

public interface TodoRepository {
	List<TodoItem> findAll();
	Optional<TodoItem> findById(Long id);
	TodoItem save(TodoItem item);
	void deleteById(Long id);
}
