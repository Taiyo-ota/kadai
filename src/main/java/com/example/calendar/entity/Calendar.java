package com.example.calendar.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "calendar") 
@Data 
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;       // "SCHEDULE"（予定） または "INCOME"（収入） / "EXPENSE"（支出）
    private String title;      // タイトル・内容（例: "バイト", "食費"）
    private LocalDate date;    // 日付
    private Integer amount;    // 金額（予定の場合は null または 0）
    private String genre;      // ジャンル（仕事、学校、食費など）
    private String color;      // 表示色（例: "#FF0000"）
    private String memo;       // メモ・詳細
}