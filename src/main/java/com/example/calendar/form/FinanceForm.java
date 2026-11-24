package com.example.calendar.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FinanceForm {

    private Integer id; // 編集・削除時に識別するために使用

    @NotBlank(message = "収支区分を選択してください")
    private String type; // "INCOME"（収入） または "EXPENSE"（支出）

    @NotNull(message = "金額を入力してください")
    @Min(value = 1, message = "金額は1円以上で入力してください")
    private Integer amount; // 金額

    @NotNull(message = "日付を選択してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; // 日付

    @NotBlank(message = "内容を入力してください")
    private String title; // 内容・店名（例：バイト代、ランチ代）

    private String genre; // カテゴリ（食費、交際費、給与など）

    private String memo; // メモ
}