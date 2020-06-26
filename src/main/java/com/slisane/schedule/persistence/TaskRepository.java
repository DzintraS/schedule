package com.slisane.schedule.persistence;

import com.slisane.schedule.rest.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task,Long> {

    public List<Task> findAll();

    public List<Task> findByDate(ZonedDateTime dateTime);

    public Task save(Task task);

    public void delete(Task task);

    public Optional<Task> findById(Task task);
}
