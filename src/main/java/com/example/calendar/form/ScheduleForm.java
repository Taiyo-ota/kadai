
package com.example.calendar.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ScheduleForm {
//edit(編集)
	private Integer id; // 編集時に使用
	private String title; // タイトル
	private LocalDate date; // 日付
	private String genre; // ジャンル
	private String memo; // メモ
}
