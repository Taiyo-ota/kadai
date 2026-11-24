
package com.example.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.calendar.entity.Finance;
import com.example.calendar.entity.User;
import com.example.calendar.form.FinanceForm;
import com.example.calendar.service.CategoryService;
import com.example.calendar.service.FinanceService;
import com.example.calendar.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/income-expense")
@RequiredArgsConstructor
public class FinanceController {

	// 収支情報を操作するService
	private final FinanceService financeService;

	// カテゴリー情報を操作するService
	private final CategoryService categoryService;

	// ユーザー情報を操作するService
	private final UserService userService;

	// 収支登録画面を表示する
	@GetMapping("/new")
	public String newFinance(Model model, HttpSession session) {

		// ログイン中のユーザーIDを取得する
		Long userId = (Long) session.getAttribute("userId");

		// ログインしていない場合はログイン画面へ移動する
		if (userId == null) {
			return "redirect:/login";
		}

		// 入力フォームを用意する
		model.addAttribute("incomeExpenseForm", new FinanceForm());

		// ログイン中のユーザーのカテゴリー一覧を取得する
		model.addAttribute("categories", categoryService.findByUserId(userId));

		// 収支登録画面を表示する
		return "income-expense-form";
	}

	// 収支の新規登録処理
	@PostMapping("/save")
	public String saveFinance(
			@ModelAttribute("incomeExpenseForm") FinanceForm form,
			HttpSession session,
			Model model) {

		// ログイン中のユーザーIDを取得する
		Long userId = (Long) session.getAttribute("userId");

		// ログインしていない場合はログイン画面へ移動する
		if (userId == null) {
			return "redirect:/login";
		}

		// ログイン中のユーザー情報を取得する
		User user = userService.findById(userId).orElse(null);

		// ユーザー情報が取得できない場合
		if (user == null) {
			session.invalidate();
			return "redirect:/login";
		}

		// 収支情報を作成する
		Finance finance = new Finance();

		// 登録したユーザーを設定する
		finance.setUser(user);

		// 金額を設定する
		finance.setAmount(form.getAmount());

		// 日付を設定する
		finance.setFinanceDate(form.getDate());

		// メモを設定する
		finance.setMemo(form.getMemo());

		// カテゴリーの設定はカテゴリーIDとの対応が決まってから追加する
		// 収入・支出の区分もEntity側の仕様が決まってから追加する

		// 収支情報をデータベースに保存する
		financeService.save(finance);

		// 登録完了後はメイン画面へ移動する
		return "redirect:/main";
	}
}
