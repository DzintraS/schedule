package com.slisane.schedule.service;

import com.slisane.schedule.persistence.TaskRepository;
import com.slisane.schedule.rest.model.Task;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
@Configuration
@EnableScheduling
public class ScheduleService {

    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> saveTasks(List<Task> tasks) {
        taskRepository.saveAll(tasks);
        return taskRepository.findAll();
    }

    public List<Task> saveTask(Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }

    public List<Task> updateTask(Task updatedTask) {
        taskRepository.save(updatedTask);
        return taskRepository.findAll();
    }

    public List<Task> updateTask(Long id, boolean isCompleted) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            task.get().setCompleted(isCompleted);
            updateFailureIndicator(task.get(), isCompleted);
            updateTask(task.get());
            log.info("Task: " + task.get() + " has been updated");
        } else {
            log.error("Cannot update task " + id + ": task not found.");
        }
        return taskRepository.findAll();
    }

    private void updateFailureIndicator(Task task, boolean isCompleted) {

        if (isCompleted) {
//            set as completed
            task.setPreviousDaysFailed(task.getDaysFailed());
            task.setDaysFailed(0);
        } else {
// reverse
            task.setDaysFailed(task.getPreviousDaysFailed());
            task.setPreviousDaysFailed(0);
        }
    }

    public List<Task> deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            log.info("Task: " + task.get() + " has been deleted.");
        } else {
            log.error("Cannot delete task " + id + ": task not found.");
        }
        return taskRepository.findAll();
    }

    public List<Task> getTasksByDate(ZonedDateTime date) {
        return taskRepository.findByDate(date);
    }

    static long zoneDateTimeDifference(ZonedDateTime d1, ZonedDateTime d2, ChronoUnit unit) {
        return unit.between(d1, d2);
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateFailureIndicator() {
        getAllTasks().forEach(task -> {
            if (!task.isCompleted()) {
                task.setDaysFailed(task.getDaysFailed() + 1);
                updateTask(task);
            }
        });
        log.info("Scheduled triggered ");
    }
//cron for 7:30 every day : "30 07 * * *"
 }

