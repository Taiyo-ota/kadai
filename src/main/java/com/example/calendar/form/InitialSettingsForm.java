package com.example.calendar.form;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitialSettingsForm {

	// ジャンル・色の一覧
	@Valid
	@NotEmpty(message = "ジャンルを1つ以上登録してください")
	private List<CategoryForm> categories = new ArrayList<>();

	// ジャンル・色の入力情報
	@Getter
	@Setter
	public static class CategoryForm {

		// ジャンル名
		@NotBlank(message = "ジャンル名を入力してください")
		@Size(max = 50, message = "ジャンル名は50文字以内で入力してください")
		private String name;

		// 色
		@NotBlank(message = "色を選択してください")
		private String color;

		// 種類
		@NotBlank(message = "種類を選択してください")
		private String type;
	}
}