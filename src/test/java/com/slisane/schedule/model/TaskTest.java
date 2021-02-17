package com.slisane.schedule.model;

import com.slisane.schedule.rest.model.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.slisane.schedule.ScheduleTestBase.MAPPER;
import static com.slisane.schedule.ScheduleTestBase.RESOURCES_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    public void testSerialization() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(RESOURCES_DIR + "/model/taskJson.json")));

        Task task = MAPPER.readValue(json, Task.class);

        assertEquals("TaskName", task.getName());
        assertEquals("WEEKLY", task.getFrequency());
        assertEquals("Description", task.getDescription());
    }
}
