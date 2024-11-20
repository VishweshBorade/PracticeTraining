package com.vb.entity;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="todo")
public class ToDo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int tid;
	
	@Column(length = 30)
	private String task;
	
	@Pattern(regexp = "^(high)|(low)|(normal)|(High)|(Normal)|(Low)$",message = "Enter valid priority")
	@Column(length = 30)
	private String priority;
	
	@Size(min = 7, max = 9)
	private String status;
	
	private LocalDate assignedDate = LocalDate.now();
	
	@NotNull(message="Date is mandatory")
	private LocalDate finishedDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "uid", referencedColumnName = "uid")
	private User user;
	
	
	public ToDo() {
		
	}


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public LocalDate getAssignedDate() {
		return assignedDate;
	}


	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}


	public LocalDate getFinishedDate() {
		return finishedDate;
	}


	public void setFinishedDate(LocalDate finishedDate) {
		this.finishedDate = finishedDate;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}
	
