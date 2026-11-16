package com.example.calendar.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CalendarForm {
    private Integer id;        // 編集時に使用
    private String type;       // 予定 / 収入 / 支出
    private String title;      // タイトル
    private LocalDate date;    // 日付
    private Integer amount;    // 金額
    private String genre;      // ジャンル
    private String memo;       // メモ
}