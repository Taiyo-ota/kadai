package com.example.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.calendar.entity.Calendar;

public interface CalendarRepository
        extends JpaRepository<Calendar, Long> {

}