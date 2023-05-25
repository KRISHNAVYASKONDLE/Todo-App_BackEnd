package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
public class TodoResource {

	TodoService td;
	TodoResource(TodoService td)
	{
		this.td=td;
	}
	
	@GetMapping(path="/users/{username}")
	public List<Todo> retrievetodos(@PathVariable String username)
	{
		return td.findByUsername(username);
		
	}
	
	@GetMapping(path="/users/{username}/todos/{id}")
	public Todo retrievetodo(@PathVariable String username,@PathVariable int id)
	{
		return td.findById(id);
	}
	
	@DeleteMapping(path="/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id)
	{
		td.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(path="/users/{username}/todos/{id}")
	public Todo UpdateTodo(@PathVariable String username,@PathVariable int id,@RequestBody Todo todo)
	{
		td.updateTodo(todo);
		System.out.println("todo is "+todo);
		return todo;
	}
	
	@PostMapping(path="/users/{username}/todos")
	public Todo CreateTodo(@PathVariable String username,@RequestBody Todo todo )
	{
	
		Todo  createtodo=td.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
	   return createtodo;
	}
	
	
	
	

}
