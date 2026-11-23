package com.example.calendar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;

    // 予定をすべて取得
    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    // 予定を登録
    public Calendar save(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    // 予定を削除
    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }
}