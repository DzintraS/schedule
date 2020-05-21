package com.slisane.schedule.service;

import com.slisane.schedule.persistence.TaskRepository;
import com.slisane.schedule.rest.model.Task;
import com.slisane.schedule.rest.model.enumeration.Frequency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private TaskRepository taskRepository;

    public Object test() {
        taskRepository.save(new Task("task1", "description1", Frequency.DAILY, ZonedDateTime.now(), false));
        return taskRepository.findAll();
    }

    public List<Task> saveTask(Task task) {
        taskRepository.save(task);
        return taskRepository.findAll();
    }

    public List<Task> updateTask(Task updatedTask) {
        taskRepository.findById(updatedTask).ifPresent(task -> taskRepository.delete(task));
        taskRepository.save(updatedTask);
        return taskRepository.findAll();
    }

    public List<Task> deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            log.info("Task: " + task + "has been deleted.");
        } else {
            log.error("Cannot delete task " + id + ": task not found.");
        }
        return taskRepository.findAll();
    }

    public List<Task> getTasksByDate(ZonedDateTime date) {
        return taskRepository.findByDate(date);
    }
}