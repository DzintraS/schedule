package com.slisane.schedule.service;

import com.slisane.schedule.persistence.TaskRepository;
import com.slisane.schedule.rest.model.Task;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.Mockito.*;
//* - imports static - imports methods from class. * - means imports all methods

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ScheduleServiceTest {
    ScheduleService scheduleService;
    TaskRepository taskRepositoryMock;

    @BeforeAll
    void before() {
        taskRepositoryMock = mock(TaskRepository.class);
        scheduleService = new ScheduleService(taskRepositoryMock);
    }

    @AfterEach
    void afterEach() {
        Mockito.reset(taskRepositoryMock);
    }

    @Test
    void testGetAllTasks() {
        scheduleService.getAllTasks();
        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void testSaveTask() {
        scheduleService.saveTask(new Task());
        verify(taskRepositoryMock, times(1)).save(any(Task.class));
        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void testUpdateTask() {
        scheduleService.updateTask(new Task());
        verify(taskRepositoryMock, times(1)).save(any(Task.class));
        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void testUpdateTaskWithTaskFound() {
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        when(taskRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Task()));
        scheduleService.updateTask(1L, true);
        verify(taskRepositoryMock, times(1)).save(captor.capture());
        Task capturedTask = captor.getValue();
        Assertions.assertTrue(capturedTask.isCompleted());
        verify(taskRepositoryMock, times(2)).findAll();
    }

    @Test
    void testUpdateTaskWithTaskNotFound() {

        when(taskRepositoryMock.findById(anyLong())).thenReturn(null);
        scheduleService.updateTask(null);
        verify(taskRepositoryMock, times(0)).save(any(Task.class));
        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void testDeleteTaskWithTaskFound() {

        when(taskRepositoryMock.findById(anyLong())).thenReturn(Optional.of(new Task()));
        scheduleService.deleteTask(1L);
        verify(taskRepositoryMock, times(1)).delete(any(Task.class));
        verify(taskRepositoryMock, times(1)).findAll();
    }

    @Test
    void testDeleteTaskWithTaskNotFound() {
        when(taskRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        scheduleService.deleteTask(null);
        verify(taskRepositoryMock, times(0)).delete(any(Task.class));
        verify(taskRepositoryMock, times(1)).findAll();
    }

}

