
package com.example.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.calendar.entity.Schedule;
import com.example.calendar.form.ScheduleForm;
import com.example.calendar.service.ScheduleService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

	// 予定情報を操作するService
	private final ScheduleService scheduleService;

	// 予定一覧を取得する
	@GetMapping
	public String showList(Model model, HttpSession session) {

		// ログイン中のユーザーIDを取得する
		Long userId = (Long) session.getAttribute("userId");

		// 予定一覧を取得する
		model.addAttribute("schedules", scheduleService.findByUserId(userId));

		return "schedule/list";
	}

	// 新規登録画面を表示する
	@GetMapping("/new")
	public String newSchedule(Model model) {
		model.addAttribute("scheduleForm", new ScheduleForm());
		return "schedule/new";
	}

	// 編集画面を表示する
	@GetMapping("/edit/{id}")
	public String editSchedule(@PathVariable Long id, Model model) {

		Schedule schedule = scheduleService.findById(id);

		model.addAttribute("schedule", schedule);

		return "schedule/edit";
	}

	// 新規登録・更新処理
	@PostMapping("/save")
	public String saveSchedule(
			@ModelAttribute ScheduleForm form,
			HttpSession session) {

		// ログイン中のユーザーIDを取得する
		Long userId = (Long) session.getAttribute("userId");

		// 予定情報を作成する
		Schedule schedule = new Schedule();

		if (form.getId() != null) {
			schedule.setId(form.getId());
		}

		schedule.setTitle(form.getTitle());
		schedule.setScheduleDate(form.getDate());
		schedule.setMemo(form.getMemo());

		// ユーザー情報を設定する
		// ※ カテゴリー設定は後で追加

		scheduleService.save(schedule);

		return "redirect:/schedule";
	}

	// 削除処理
	@PostMapping("/delete/{id}")
	public String deleteSchedule(@PathVariable Long id) {
		scheduleService.delete(id);
		return "redirect:/schedule";
	}
}
