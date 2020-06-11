package com.slisane.schedule.rest.model;

import com.slisane.schedule.rest.model.enumeration.Frequency;
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
    private Frequency frequency;

    //    @NotNull
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{6}\\+\\d{2}:\\d{2}", message = "date does not match pattern") //TODO add validation
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

    @Override
    public String toString() {
        return String.format("[(%d) %s | freq: %s | date: %s | done: %s ]", id, name, frequency, date, isCompleted);
    }

    public String isCompletedToString() {
        return isCompleted ? "Completed" : "Not completed";
    }

    //TODO RESEARCH fix jsr303 validation messages are not returned
}