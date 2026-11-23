
package com.example.calendar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.calendar.entity.Category;
import com.example.calendar.entity.User;
import com.example.calendar.form.InitialSettingsForm;
import com.example.calendar.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	// カテゴリー情報を操作するRepository
	private final CategoryRepository categoryRepository;

	// 複数のカテゴリーを登録する
	public void registerCategories(User user, InitialSettingsForm form) {

		// 登録するカテゴリー一覧
		List<Category> categories = new ArrayList<>();

		// 入力されたカテゴリーを1件ずつ処理する
		for (InitialSettingsForm.CategoryForm categoryForm : form.getCategories()) {

			// カテゴリー情報を作成する
			Category category = new Category();

			// ユーザー情報を設定する
			category.setUser(user);

			// ジャンル名を設定する
			category.setName(categoryForm.getName());

			// 色を設定する
			category.setColor(categoryForm.getColor());

			// 種類を設定する
			category.setType(categoryForm.getType());

			// 登録一覧に追加する
			categories.add(category);
		}

		// データベースにまとめて登録する
		categoryRepository.saveAll(categories);
	}

	// ユーザーIDからカテゴリー一覧を取得する
	public List<Category> findByUserId(Long userId) {
		return categoryRepository.findByUserId(userId);
	}
}
