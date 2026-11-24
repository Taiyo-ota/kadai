package com.example.calendar.form;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScheduleForm {

    private Integer id; // 編集・削除時に識別するために使用

    @NotBlank(message = "予定タイトルを入力してください")
    private String title; // 予定タイトル（キーワード自動色分けの判定対象）

    @NotNull(message = "日付を選択してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 日付

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime; // 開始時間

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime; // 終了時間

    private String genre; // ジャンル（学校・仕事・プライベート等）

    private String memo; // 内容・メモ
}