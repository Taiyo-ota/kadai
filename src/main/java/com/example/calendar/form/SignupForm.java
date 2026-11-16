package com.example.calendar.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {

	// メールアドレス
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しいメールアドレスを入力してください")
	@Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
	private String email;

	// ユーザー名
	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	private String name;

	// パスワード
	@NotBlank(message = "パスワードを入力してください")
	private String password;

	// パスワード確認用
	@NotBlank(message = "確認用パスワードを入力してください")
	private String passwordConfirm;

}