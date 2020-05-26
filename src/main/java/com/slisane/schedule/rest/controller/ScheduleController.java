package com.slisane.schedule.rest.controller;

import com.slisane.schedule.rest.model.Task;
import com.slisane.schedule.service.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;

    @GetMapping(value = "/test")
    public Object testController() {
        return scheduleService.test();
    }

    @PostMapping(value = "/task")
    public Object saveTask(@RequestBody Task task) {
        return scheduleService.saveTask(task);
    }

    @PatchMapping(value = "/task")
    public Object updateTask(
            @Valid @RequestBody Task task) {
        return scheduleService.updateTask(task);
    }

    @DeleteMapping(value = "/task")
    public Object deleteTask(@RequestParam(value = "id") Long id) {
        return scheduleService.deleteTask(id);
    }

    @PostMapping(value = "/taskByDate")
    public Object getTasksByDate() {
        return scheduleService.test();
    }

    @GetMapping(value = "/createTask")
    public Object Task() {
        return " just testing";

    }


    //TODO add logic for getting by days
    //TODO RESEARCH ZonedDateTime , Formatter and validation


}
