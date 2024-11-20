package com.vb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vb.entity.ToDo;
import com.vb.entity.User;
import com.vb.repo.ToDoRepository;
import com.vb.util.TaskException;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository repo;
	
	@Autowired
	private UserService uservice;
	
	@Override
	public void save(ToDo t, int uid) throws TaskException {
		User u = uservice.fetch(uid);
		t.setUser(u);
		t.setStatus("pending");
		repo.save(t);
	}

	@Override
	public ToDo fetch(int tid) {
		return repo.findById(tid).get();
	}

	@Override
	public void update(int tid) {
		ToDo t = fetch(tid);
		t.setStatus("Completed");
		repo.save(t);
	}

	@Override
	public void deleteBytid(int tid) {
		repo.deleteById(tid);
	}

	@Override
	public List<ToDo> findByUser(int uid) {
		return repo.findByUser(uid);
	}

	@Override
	public List<ToDo> findByStatus(String status) {
		return repo.findByStatus(status);
	}

}
