package com.seahere.backend.alarm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "alarm_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlarmHistoryEntity {

    @Id @GeneratedValue
    private Long alarmHistoryId;
    private Long userId;
    private String title;
    private String body;



}
