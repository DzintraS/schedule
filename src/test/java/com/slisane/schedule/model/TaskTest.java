package com.slisane.schedule.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.slisane.schedule.rest.model.Task;
import com.slisane.schedule.rest.model.enumeration.Deadlines;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.slisane.schedule.ScheduleTestBase.MAPPER;
import static com.slisane.schedule.ScheduleTestBase.RESOURCES_DIR;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void testDeserialization() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get(RESOURCES_DIR + "/model/taskJson.json")));

        Task task = MAPPER.readValue(json, Task.class);

        assertEquals("TaskName", task.getName());
        assertEquals("WEEKLY", task.getFinishThis().toString());
        assertEquals("Description", task.getDescription());
    }

    @Test
    void testSerialization() throws IOException, JSONException {
        Task task = new Task();
        task.setName("TaskName");
        task.setFinishThis(Deadlines.WEEK);
        task.setDescription("Description");

        String json = MAPPER.writeValueAsString(task);
        String expected = new String(Files.readAllBytes(Paths.get(RESOURCES_DIR + "/model/taskJson.json")));

        JSONAssert.assertEquals(expected,json, JSONCompareMode.LENIENT);

    }


}
