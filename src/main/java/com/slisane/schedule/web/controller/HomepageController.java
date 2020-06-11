package com.slisane.schedule.web.controller;

import com.slisane.schedule.rest.controller.ScheduleController;
import com.slisane.schedule.rest.model.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class HomepageController {

    ScheduleController scheduleController;

    @GetMapping("/")
    public String homepage(Model model) {
        //this is a method that returns a homepage. Model contains attributes that are used to create it

        //getting tasks from our rest controller
        List<Task> todayTasks = scheduleController.getAlltasks();

        //sorting through them using stream and adding them to the Model as attributes
        model.addAttribute("todayTasksNotDone", todayTasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList())
        );
        model.addAttribute("todayTasksDone", todayTasks.stream()
                .filter(task-> task.isCompleted())
                .collect(Collectors.toList())
        );

        // returning the name of html file to be built using the Model,
        // located in resources/templates/homepage.html
        return "homepage";
    }
}
