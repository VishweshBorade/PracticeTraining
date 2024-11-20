package com.vb.service;

import java.util.List;

import com.vb.entity.User;
import com.vb.util.UserException;

public interface UserService {
	void save(User u) throws UserException;
	
	User fetch(int uid);
	
	void deleteByUid(int uid);
	
	List<User> list();
}
