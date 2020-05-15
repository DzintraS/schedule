package com.slisane.schedule.model;

import com.slisane.schedule.model.enumeration.Frequence;

import java.time.ZonedDateTime;

public class Task {

    private String name;
    private String description;
    private Frequence frequence;
    private ZonedDateTime date;
    private boolean isCompleted;

    public Task() {
    }

    public Task(String name, String description, Frequence frequence, ZonedDateTime date, boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.frequence = frequence;
        this.date = date;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Frequence getFrequence() {
        return frequence;
    }

    public void setFrequence(Frequence frequence) {
        this.frequence = frequence;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
