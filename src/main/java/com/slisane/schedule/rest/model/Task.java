package com.slisane.schedule.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.slisane.schedule.rest.model.enumeration.Deadlines;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    private String description;

    private ZonedDateTime date;

    private ZonedDateTime deadline;

    private boolean isCompleted;
    //    Added deadline instead of frequency.
    private Deadlines finishThis;

    public Task(Long id, @NotBlank String name, String description, @NotNull() Deadlines finishThis, ZonedDateTime date, ZonedDateTime deadline, boolean isCompleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.finishThis = finishThis;
        this.date = date;
        this.deadline = calculateDeadline();
        this.isCompleted = isCompleted;
    }

    //TODO implement code so that
    public String isCompletedToString() {
        return isCompleted ? "Completed" : "Not completed";
    }

    public ZonedDateTime calculateDeadline() {
        switch (finishThis) {
            case DAY:
                this.deadline = date.plusDays(1);
            case WEEK:
                this.deadline = date.plusWeeks(1);
            case MONTH:
                this.deadline = date.plusMonths(1);
        }
        return deadline;
    }
}