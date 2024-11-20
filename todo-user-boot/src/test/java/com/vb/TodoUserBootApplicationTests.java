package com.vb;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.vb.entity.ToDo;
import com.vb.service.ToDoService;
import com.vb.util.TaskException;
import com.vb.web.ToDoController;

@SpringBootTest
class TodoUserBootApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Mock
	private ToDoService service;

	@InjectMocks
	private ToDoController todocontoller;

	private ToDo todo;

	@BeforeEach
	void setup() {
		todo = new ToDo();
		todo.setTid(1);
		todo.setTask("math");
		todo.setPriority("high");
		todo.setStatus("pending");
		todo.setAssignedDate(LocalDate.parse("2024-11-19"));
		todo.setFinishedDate(LocalDate.parse("2025-12-12"));
	}

	@Test
	void save() throws TaskException {
		doNothing().when(service).save(any(ToDo.class), eq(1));
		ResponseEntity<?> response = todocontoller.save(todo, 1);
		assertEquals("Task saved", response);
		verify(service, times(1)).save(any(ToDo.class), eq(1));
	}
}