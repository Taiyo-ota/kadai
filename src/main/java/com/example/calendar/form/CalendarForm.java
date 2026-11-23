package com.example.calendar.form;

import java.time.LocalDate;
import java.time.LocalTime;
<<<<<<< HEAD

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CalendarForm {
=======
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git

<<<<<<< HEAD
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

=======
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
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git
}