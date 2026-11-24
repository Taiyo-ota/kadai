package com.example.calendar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Finance")
public class Finance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id",nullable = false)
	private String userId;
	
	//収支か支出なのかを区別する
	@Column(nullable = false)
	private String type;
	
	@Column(nullable = false)
	private String amount;
	
	@Column(nullable = false)
	private String date;
	
	@Column(length = 50)
	private String category;
	
	@Column(columnDefinition = "TEXT")
	private String memo;

}
