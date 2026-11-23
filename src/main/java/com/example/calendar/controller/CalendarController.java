package com.example.calendar.controller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.calendar.entity.Calendar;
import com.example.calendar.form.CalendarForm;
import com.example.calendar.service.CalendarService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.calendar.entity.Calendar;
import com.example.calendar.form.CalendarForm;
import com.example.calendar.service.CalendarService;

@Controller
@RequestMapping("/calendar")
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git
public class CalendarController {

<<<<<<< HEAD
    private final CalendarService calendarService;

    @GetMapping("/main")
    public String main(
            @SessionAttribute(name = "userId", required = false) Long userId,
            Model model) {

        if (userId == null) {
            return "redirect:/login";
        }

        List<Calendar> schedules = calendarService.findAll();

        schedules.removeIf(schedule ->
                !userId.equals(schedule.getUserId()));

        model.addAttribute("schedules", schedules);

        return "main";
    }

    @GetMapping("/schedule/new")
    public String scheduleForm(
            @SessionAttribute(name = "userId", required = false) Long userId,
            Model model) {

        if (userId == null) {
            return "redirect:/login";
        }

        model.addAttribute("scheduleForm", new CalendarForm());

        return "schedule-form";
    }

    @PostMapping("/schedule/new")
    public String saveSchedule(
            @SessionAttribute(name = "userId", required = false) Long userId,
            @ModelAttribute CalendarForm form) {

        if (userId == null) {
            return "redirect:/login";
        }

        Calendar calendar = new Calendar();

        calendar.setUserId(userId);
        calendar.setTitle(form.getTitle());
        calendar.setGenre(form.getGenre());
        calendar.setDate(form.getDate());
        calendar.setStartTime(form.getStartTime());
        calendar.setEndTime(form.getEndTime());
        calendar.setMemo(form.getMemo());

        calendarService.save(calendar);

        return "redirect:/main";
=======
    @Autowired
    private CalendarService service;

    // 1. メイン画面表示
    @GetMapping
    public String showMain(Model model) {
        model.addAttribute("items", service.findAll());
        model.addAttribute("calendarForm", new CalendarForm()); // 入力フォーム用
        return "main"; // templates/main.html を表示
    }

    // 2. 予定・収支の新規登録処理
    @PostMapping("/save")
    public String saveItem(@ModelAttribute CalendarForm form) {
        service.save(form);
        return "redirect:/calendar"; // 登録後、メイン画面に戻る
    }

    // 3. 編集画面の表示
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Integer id, Model model) {
        Calendar item = service.findById(id);
        model.addAttribute("item", item);
        return "edit"; // templates/edit.html を表示
    }

    // 4. 削除処理
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Integer id) {
        service.delete(id);
        return "redirect:/calendar"; // 削除後、メイン画面に戻る
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git
    }
}