package com.slisane.schedule.rest.model;

import com.slisane.schedule.rest.model.enumeration.Frequency;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Frequency frequency;

    private ZonedDateTime date;

    private boolean isCompleted;

    public Task(String name, String description, Frequency frequency, ZonedDateTime date, boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.date = date;
        this.isCompleted = isCompleted;
    }

    public void createTask(String task1, String description, Frequency daily, ZonedDateTime now, boolean b) {
    }
}