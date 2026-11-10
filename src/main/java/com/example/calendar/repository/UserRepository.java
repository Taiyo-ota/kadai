package com.example.calendar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.calendar.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// メールアドレスからユーザーを検索する
	Optional<User> findByEmail(String email);

}