package com.example.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

	// 新規登録画面を表示する
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	// 登録後は初期設定画面へ移動する（仮）
	@GetMapping("/signup/complete")
	public String signupComplete() {
		return "redirect:/initial-settings";
	}

	// 初期設定完了後はメイン画面へ移動する（仮）
	@GetMapping("/initial-settings/complete")
	public String initialSettingsComplete() {
		return "redirect:/main";
	}

	// 初期設定画面を表示する
	@GetMapping("/initial-settings")
	public String initialSettings() {
		return "initial-settings";
	}
}