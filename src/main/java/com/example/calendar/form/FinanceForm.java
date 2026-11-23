package com.example.calendar.form;

import java.time.LocalDate;

import lombok.Data;
//収支・支出
@Data
public class FinanceForm {
    private Integer id;          // 編集時に使用
    private String type;         // 区分（"INCOME"：収入 / "EXPENSE"：支出）
    private Integer amount;      // 金額
    private LocalDate date;      // 日付
    private String title;        // 内容・店名など
    private String genre;        // カテゴリ（食費、バイト代など）
    private String memo;         // メモ
}
