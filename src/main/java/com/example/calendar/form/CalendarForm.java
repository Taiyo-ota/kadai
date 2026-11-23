package com.example.calendar.form;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;//スケジュール
@Data
public class CalendarForm {
    private Integer id;        // 編集時に使用
    private String title;      // タイトル
    private LocalDate date;    // 日付
    private LocalTime startTime;//開始時間
    private LocalTime endTime;//終了時間
    private String genre;      // ジャンル
    private String memo;       // メモ
}