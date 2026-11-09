
package com.example.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	// ログイン画面を表示する
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// 新規登録画面を表示する
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	// 初期設定画面を表示する
	@GetMapping("/initial-settings")
	public String initialSettings() {
		return "initial-settings";
	}

	// メイン画面を表示する
	@GetMapping("/main")
	public String main() {
		return "main";
	}
}
