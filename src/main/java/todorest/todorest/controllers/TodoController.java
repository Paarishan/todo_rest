package todorest.todorest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import todorest.todorest.Entities.Todo;
import todorest.todorest.dtos.TodoDto;
import todorest.todorest.repositories.TodoRepo;


@RestController
public class TodoController{
	
	@Autowired
	private TodoRepo todoRepo;
	
	@GetMapping("/todos")
	public List<Todo> todos(@RequestParam Optional<Boolean> completed) {
		return completed.map(c ->this.todoRepo.findAllByCompleted(c))
		.orElseGet(() ->this.todoRepo.findAll());
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
		return this.todoRepo.findById(id)
		.map(todo -> ResponseEntity.ok().body(todo))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/todos")
	public ResponseEntity<Object> addTodo(@Valid @RequestBody TodoDto todoDto){
		long id = 0;
		Todo todo=new Todo(id,todoDto.getTask(),false);
		this.todoRepo.save(todo);
		
		return ResponseEntity.ok().build();
		
	}
	
//	@RequestMapping(value ="/todos{id}",method = RequestMethod.PUT)
//	public ResponseEntity<Object> updateTodo( @PathVariable Long id,@Valid @RequestBody TodoDto todoDto){
//		return this.todoRepo.findById(id)
//		.map(existingtodo -> {
//			existingtodo.setTask(todoDto.getTask());
//		this.todoRepo.save(existingtodo);
//		return ResponseEntity.ok().build();
//		})
//		.orElse(ResponseEntity.notFound().build());
//	}
	
	@PutMapping("/todos/{id}")
    public ResponseEntity<Object> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        Optional<Todo> optionalTodo = todoRepo.findById(id);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Todo existingTodo = optionalTodo.get();
        existingTodo.setTask(todoDto.getTask());
        todoRepo.save(existingTodo);

        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/todos/{id}/mark_completed")
    public ResponseEntity<Object> markCompleted(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        Optional<Todo> optionalTodo = todoRepo.findById(id);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Todo existingTodo = optionalTodo.get();
        existingTodo.setCompleted(true);
        todoRepo.save(existingTodo);

        return ResponseEntity.ok().build();
    }
	
	@PutMapping("/todos/{id}/mark_incomplete")
    public ResponseEntity<Object> markIncomplete(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        Optional<Todo> optionalTodo = todoRepo.findById(id);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Todo existingTodo = optionalTodo.get();
        existingTodo.setCompleted(false);
        todoRepo.save(existingTodo);

        return ResponseEntity.ok().build();
    }
	
	@DeleteMapping("/todos/{id}")
    public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
        Optional<Todo> optionalTodo = todoRepo.findById(id);
        if (!optionalTodo.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        todoRepo.deleteById(id);

        return ResponseEntity.ok().build();
    }
}