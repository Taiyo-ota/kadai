package com.example.calendar.repository;


import org.springframework.data.jpa.repository.JpaRepository;
=======
import java.util.List;
>>>>>>> branch 'main' of https://github.com/Taiyo-ota/kadai.git

<<<<<<< HEAD
import com.example.calendar.entity.Calendar;

public interface CalendarRepository
        extends JpaRepository<Calendar, Long> {

=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.calendar.entity.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    
    // 日付順で全件取得するメソッド
    List<Calendar> findAllByOrderByDateAsc();
    
    // 予定のみ・収支のみで絞り込むメソッド
    List<Calendar> findByType(String type);

}