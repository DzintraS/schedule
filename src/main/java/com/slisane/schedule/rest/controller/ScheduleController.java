package com.slisane.schedule.rest.controller;

import com.slisane.schedule.rest.model.Task;
import com.slisane.schedule.service.ScheduleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
//@RequestMapping("/")
@AllArgsConstructor
public class ScheduleController {

    private ScheduleService scheduleService;
//    private Task task;

    @GetMapping(value = "/test")
    public Object testController() {
        return scheduleService.test();
    }

    @PostMapping(value = "/task")
    public Object saveTask(@RequestBody Task task) {
        return scheduleService.saveTask(task);
    }

    @PatchMapping(value = "/task")
    public Object updateTask(@RequestBody Task task) {
        return scheduleService.updateTask(task);
    }

    @GetMapping(value = "/test")
    public Object deleteTask(@RequestParam(value = "id") Long id) {
        return scheduleService.deleteTask(id);
    }

    @GetMapping(value = "/test")
    public Object getTasksByDate() {
        return scheduleService.test();
    }

    @GetMapping(value = "/createTask")
    public Object Task() {
        return " just testing";

    }


}
