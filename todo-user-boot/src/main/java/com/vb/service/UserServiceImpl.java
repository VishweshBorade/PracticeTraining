package com.vb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vb.entity.User;
import com.vb.repo.userRepository;
import com.vb.util.UserException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private userRepository repo;
	
	@Override
	public void save(User u) throws UserException{
		repo.save(u);
	}

	@Override
	public User fetch(int uid) {
		return repo.findById(uid).get();
	}

	@Override
	public void deleteByUid(int uid) {
		repo.deleteById(uid);
	}

	@Override
	public List<User> list() {
		return repo.findAll();
	}

}
