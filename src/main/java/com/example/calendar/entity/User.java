package com.example.calendar.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

	// ユーザーID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// メールアドレス
	@Column(nullable = false, unique = true, length = 255)
	private String email;

	// パスワード
	@Column(nullable = false, length = 255)
	private String password;

	// ユーザー名
	@Column(nullable = false, length = 50)
	private String name;

	// 登録日時
	@Column(name = "created_at", insertable = false, updatable = false)
	private LocalDateTime createdAt;

}