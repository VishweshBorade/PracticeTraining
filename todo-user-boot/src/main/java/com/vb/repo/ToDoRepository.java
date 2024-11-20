package com.vb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vb.entity.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
	
	@Query("FROM ToDo WHERE user.uid=:uid")
	List<ToDo> findByUser(@Param("uid")int uid);
	
	@Query("FROM ToDo WHERE status=:status")
	List<ToDo> findByStatus(@Param("status") String status);
}
