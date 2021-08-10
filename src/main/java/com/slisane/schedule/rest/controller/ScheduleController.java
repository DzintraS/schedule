package com.slisane.schedule.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slisane.schedule.rest.model.Task;
import com.slisane.schedule.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

//@Slf4js
@Validated
@RestController
@AllArgsConstructor
@RequestMapping(value = ScheduleController.BASE_URL)
public class ScheduleController {
    public static final String BASE_URL = "/rest";

    private ScheduleService scheduleService;

    private ObjectMapper mapper = new ObjectMapper();

    //CRUD
    //Create object
    @PostMapping(value = "/task")
    public Object saveTask(@RequestBody Task task) {
        if (task.getDate() == null) {
            task.setDate(ZonedDateTime.now());
        }
        return scheduleService.saveTask(task);
    }

    //Read
    @GetMapping(value = "/task")
    public List<Task> getAlltasks() {
        return scheduleService.getAllTasks();
    }

    @GetMapping(value = "/taskByDate")
    public List<Task> getTasksByDate(@RequestParam(value = "date") ZonedDateTime date) {
        return scheduleService.getTasksByDate(date);
    }

    //Update
    @PatchMapping(value = "/task")
    public Object updateTask(
            @Valid @RequestBody Task task) {
        return scheduleService.updateTask(task);
    }

    @PatchMapping(value = "/task/setCompleted")
    public Object setTaskCompleted(@NotNull @RequestParam(value = "id") Long id) {
        return scheduleService.updateTask(id, true);
    }

    @PatchMapping(value = "/task/setNotCompleted")
    public Object setTaskNotCompleted(@NotNull @RequestParam(value = "id") Long id) {
        return scheduleService.updateTask(id, false);
    }

    //Delete
    @DeleteMapping(value = "/task")
    public Object deleteTask(@NotNull @RequestParam(value = "id") Long id) {
        return scheduleService.deleteTask(id);
    }

    }

