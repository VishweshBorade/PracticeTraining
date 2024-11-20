package com.vb.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vb.entity.User;

public interface userRepository extends JpaRepository<User, Integer> {
	
}
