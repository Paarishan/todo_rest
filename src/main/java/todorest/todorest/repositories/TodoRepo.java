package todorest.todorest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import todorest.todorest.Entities.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long>{
	
	public List<Todo> findAllByCompleted(Boolean completed);
	
}