package com.example.calendar.form;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CalendarForm {

    // 予定ID
    private Long id;

    // 予定名
    private String title;

    // ジャンルID
    private Long categoryId;

    // ジャンル名（画面表示用）
    private String genre;

    // 日付
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // 開始時間
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    // 終了時間
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    // 予定の色
    private String color = "#4169e1";

    // メモ
    private String memo;

}