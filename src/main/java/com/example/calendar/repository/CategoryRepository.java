package com.example.calendar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.calendar.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	// ユーザーIDからカテゴリー一覧を取得する
	List<Category> findByUserId(Long userId);
}