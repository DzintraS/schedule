package com.slisane.schedule.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull()
    private String frequency;

    //    @NotNull
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}\\+\\d{2}:\\d{2}", message = "date does not match pattern")
    private ZonedDateTime date;

    private boolean isCompleted;

    private int daysFailed;

    private int previousDaysFailed;
    private String daysFailedAsString;

    public Task(Long id, @NotBlank String name, String description, @NotNull() String frequency, ZonedDateTime date, boolean isCompleted, int daysFailed, int previousDaysFailed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.date = date;
        this.isCompleted = isCompleted;
        this.daysFailed = daysFailed;
        this.previousDaysFailed = previousDaysFailed;
    }

    @Override
    public String toString() {
        return String.format("[(%d) %s | freq: %s | date: %s | done: %s ]", id, name, frequency, date, isCompleted);
    }

    public String isCompletedToString() {
        return isCompleted ? "Completed" : "Not completed";
    }


}