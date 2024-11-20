package com.vb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vb.entity.ToDo;
import com.vb.service.ToDoService;
import com.vb.util.TaskException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todo")
public class ToDoController {

	@Autowired
	private ToDoService service;

	@PostMapping( value = "/save/{id}", consumes = "application/json")
	public ResponseEntity<?> save(@Valid @RequestBody ToDo t, @PathVariable int id) throws TaskException {
		service.save(t, id);
		return ResponseEntity.ok("Task saved");
	}
	
	@GetMapping(value="/fetch",produces = "application/json")
	public ToDo fetch(int id) {
		return service.fetch(id);
	}
	@DeleteMapping("/delete")
	public void delete(int id) {
		 service.deleteBytid(id);
	}
	
	@PutMapping("/update/{id}")
	public String update(@PathVariable int id) {
		service.update(id);
		return "Status updated";
	}
	
	@GetMapping(value = "/findByUser/{uid}",produces = "application/json")
	public List<ToDo> list(@PathVariable int uid){
		return service.findByUser(uid);
	}
	
	@GetMapping(value="/findByStatus/{status}",produces = "application/json")
	public List<ToDo> list(@PathVariable String status){
		return service.findByStatus(status);
	}
}
