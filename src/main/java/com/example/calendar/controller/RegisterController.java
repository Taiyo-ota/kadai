package com.example.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.calendar.form.SignupForm;
import com.example.calendar.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {

	// ユーザー情報を操作するService
	private final UserService userService;

	// 新規登録画面を表示する
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "signup";
	}

	// 新規登録処理
	@PostMapping("/signup")
	public String register(
			@Validated @ModelAttribute("signupForm") SignupForm form,
			BindingResult result,
			Model model) {

		// 入力チェック
		if (result.hasErrors()) {
			return "signup";
		}

		// パスワードと確認用パスワードが一致するか確認
		if (!form.getPassword().equals(form.getPasswordConfirm())) {
			model.addAttribute("passwordError", "パスワードが一致しません");
			return "signup";
		}

		// ユーザー登録
		boolean registered = userService.register(form);

		// メールアドレスがすでに登録されている場合
		if (!registered) {
			model.addAttribute("emailError", "このメールアドレスはすでに登録されています");
			return "signup";
		}

		// 登録後は初期設定画面へ移動
		return "redirect:/initial-settings";
	}

	// 初期設定画面を表示する
	@GetMapping("/initial-settings")
	public String initialSettings() {
		return "initial-settings";
	}

	// 初期設定完了後はメイン画面へ移動する
	@PostMapping("/initial-settings")
	public String initialSettingsComplete() {
		return "redirect:/main";
	}
}