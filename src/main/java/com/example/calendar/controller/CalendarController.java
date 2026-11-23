package com.example.calendar.controller;

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
public class CalendarController {

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
    }
}