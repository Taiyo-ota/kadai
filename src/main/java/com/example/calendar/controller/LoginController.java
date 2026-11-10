package com.example.calendar.controller;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.calendar.entity.User;
import com.example.calendar.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	// ユーザー情報を操作するRepository
	private final UserRepository userRepository;

	// パスワードを照合する
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// ログイン画面を表示する
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// ログイン処理
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			Model model) {

		// メールアドレスからユーザーを検索する
		Optional<User> result = userRepository.findByEmail(email);

		// ユーザーが存在しない場合
		if (result.isEmpty()) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います");
			return "login";
		}

		User user = result.get();

		// パスワードを照合する
		if (!passwordEncoder.matches(password, user.getPassword())) {
			model.addAttribute("loginError", "メールアドレスまたはパスワードが違います");
			return "login";
		}

		// ログイン情報をセッションに保存する
		session.setAttribute("userId", user.getId());
		session.setAttribute("userName", user.getName());

		// ログイン成功後はメイン画面へ移動する
		return "redirect:/main";
	}

	// ログアウト処理
	@PostMapping("/logout")
	public String logout(HttpSession session) {

		// セッションを破棄する
		session.invalidate();

		return "redirect:/login";
	}
}