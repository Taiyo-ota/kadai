package com.example.calendar.service;

import java.util.List;

<<<<<<< HEAD
import org.springframework.stereotype.Service;

import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.calendar.entity.Calendar;
import com.example.calendar.form.CalendarForm;
import com.example.calendar.repository.CalendarRepository;

@Service

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
    @Autowired
    private CalendarRepository repository;

    // 一覧取得（メイン画面用）
    public List<Calendar> findAll() {
        return repository.findAllByOrderByDateAsc();
    }

    // 1件取得（編集画面用）
    public Calendar findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // 新規登録・更新処理
    public void save(CalendarForm form) {
        Calendar item = new Calendar();
        
        // IDがある場合は「更新（編集）」、なければ「新規登録」
        if (form.getId() != null) {
            item.setId(form.getId());
        }
        
        item.setType(form.getType());
        item.setTitle(form.getTitle());
        item.setDate(form.getDate());
        item.setAmount(form.getAmount());
        item.setGenre(form.getGenre());
        item.setMemo(form.getMemo());
        
        //色分けロジックを呼ぶ
        item.setColor("#3B82F6"); // 仮のデフォルト色

        repository.save(item);
    }

    // 削除処理
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}