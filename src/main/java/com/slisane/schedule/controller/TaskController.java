package com.slisane.schedule.controller;

import com.slisane.schedule.model.Task;
import com.slisane.schedule.model.enumeration.Frequence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class TaskController {

    @GetMapping(value="/getTask")
    public Object testController() {
        return new Task("Task1", "description", Frequence.DAILY, ZonedDateTime.now(), true);
    }



}
