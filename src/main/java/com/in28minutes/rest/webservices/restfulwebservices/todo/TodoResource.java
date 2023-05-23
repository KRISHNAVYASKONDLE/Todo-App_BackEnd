package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	
	
	
}
