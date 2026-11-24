
package com.example.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.calendar.entity.User;
import com.example.calendar.form.InitialSettingsForm;
import com.example.calendar.form.SignupForm;
import com.example.calendar.service.CategoryService;
import com.example.calendar.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterController {

	// ユーザー情報を操作するService
	private final UserService userService;

	// カテゴリー情報を操作するService
	private final CategoryService categoryService;

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
			Model model,
			HttpSession session) {

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
		User user = userService.register(form).orElse(null);

		// メールアドレスがすでに登録されている場合
		if (user == null) {
			model.addAttribute("emailError", "このメールアドレスはすでに登録されています");
			return "signup";
		}
		// 登録したユーザーIDをセッションに保存する
		session.setAttribute("initialSettingsUserId", user.getId());

		// 登録したユーザーのメールアドレスをセッションに保存する
		session.setAttribute("initialSettingsEmail", user.getEmail());

		// 登録後は初期設定画面へ移動
		return "redirect:/initial-settings";
	}

	// 初期設定画面を表示する
	@GetMapping("/initial-settings")
	public String initialSettings(Model model, HttpSession session) {

		// ユーザー登録が完了しているか確認する
		if (session.getAttribute("initialSettingsUserId") == null) {
			return "redirect:/signup";
		}

		// 初期設定フォームを用意する
		model.addAttribute("initialSettingsForm", new InitialSettingsForm());

		return "initial-settings";
	}

	// 初期設定完了後はログイン画面へ移動する
	@PostMapping("/initial-settings")
	public String initialSettingsComplete(
			@Validated @ModelAttribute("initialSettingsForm") InitialSettingsForm form,
			BindingResult result,
			Model model,
			HttpSession session) {

		// ユーザー登録が完了しているか確認する
		Long userId = (Long) session.getAttribute("initialSettingsUserId");

		if (userId == null) {
			return "redirect:/signup";
		}

		// 入力チェック
		if (result.hasErrors()) {
			return "initial-settings";
		}

		// 登録したユーザー情報を取得する
		User user = userService.findByEmail(
				(String) session.getAttribute("initialSettingsEmail"))
				.orElse(null);

		// ユーザー情報が取得できない場合
		if (user == null || !user.getId().equals(userId)) {
			session.removeAttribute("initialSettingsUserId");
			return "redirect:/signup";
		}

		// カテゴリーを登録する
		categoryService.registerCategories(user, form);

		// 初期設定用のセッション情報を削除する
		session.removeAttribute("initialSettingsUserId");
		session.removeAttribute("initialSettingsEmail");

		// ログイン画面へ移動する
		return "redirect:/login";
	}
}
