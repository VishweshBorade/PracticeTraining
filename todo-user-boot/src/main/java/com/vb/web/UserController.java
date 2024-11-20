package com.vb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vb.entity.User;
import com.vb.service.UserService;
import com.vb.util.UserException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;
	
	@PostMapping(value = "/save",consumes = "application/json")
	public String save(@RequestBody User u) throws UserException {
		service.save(u);
		return "User saved";
	}
	
	@GetMapping(value = "/fetch",consumes="application/json")
	public User fetch(int id) {
		return service.fetch(id);
	}
	
	@DeleteMapping(value = "/remove",consumes="application/json")
	public String delete(int id) {
		service.deleteByUid(id);
		return "User deleted";
	}
}
