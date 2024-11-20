package com.vb.service;

import java.util.List;

import com.vb.entity.ToDo;
import com.vb.util.TaskException;

public interface ToDoService {
	void save(ToDo t, int uid) throws TaskException;
	
	ToDo fetch(int tid);
	
	void update(int tid);
	
	void deleteBytid(int tid);
	
	List<ToDo> findByUser(int uid);
	
	List<ToDo> findByStatus(String status);
}
