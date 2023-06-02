package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.io.Console;
import java.util.List;
import java.util.Optional;

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
public class TodoResourceJpa {

	TodoRepository tr;
	TodoResourceJpa(TodoRepository tr)
	{
		this.tr=tr;
	}
	
	@GetMapping(path="/basicauth")
	public String validatetoken()
	{
		return "valid token";
	}
	
	@GetMapping(path="/users/{username}")
	public List<Todo> retrievetodos(@PathVariable String username)
	{
		return tr.findByUsername(username);	
	}
	
	@GetMapping(path="/users/{username}/todos/{id}")
	public Optional<Todo> retrievetodo(@PathVariable String username,@PathVariable int id)
	{
//			System.out.println("id is "+id);
			return tr.findById(id);
	}
	
	@DeleteMapping(path="/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id)
	{
		tr.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(path="/users/{username}/todos/{id}")
	public Todo UpdateTodo(@PathVariable String username,@PathVariable int id,@RequestBody Todo todo)
	{
		
		tr.save(todo);
//		System.out.println("todo is "+todo);
		return todo;
	}	
	
	@PostMapping(path="/users/{username}/todos")
	public Todo CreateTodo(@PathVariable String username,@RequestBody Todo todo )
	{
		todo.setUsername(username);
		Todo  createtodo=tr.save(todo);
	   return createtodo;
	}
	
	
	
	

}
