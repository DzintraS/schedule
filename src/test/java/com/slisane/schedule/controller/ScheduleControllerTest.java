package com.slisane.schedule.controller;

import com.slisane.schedule.rest.controller.ScheduleController;
import com.slisane.schedule.service.ScheduleService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.AssertTrue;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScheduleController.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ScheduleControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private ScheduleService scheduleServiceMock;
    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    //   Status 2xx successful indicates the action requested by the client
    //   was received, understood, and accepted
    @Test
    void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/rest/task"))
                .andExpect(status().is2xxSuccessful());
        verify(scheduleServiceMock, times(1)).getAllTasks();
    }

//    @Test
//    void testGetTasksByDate() throws Exception {
////        ZonedDateTime date = ZonedDateTime.now();
//////        TODO Figure out how to pass parameter ZoneDateTime to test and if that is possible
//////        mockMvc.perform(get("/rest/taskByDate").param("date", "")
////////                .andExpect(status().is2xxSuccessful());
////        verify(scheduleServiceMock, times(1)).getTasksByDate(date);
//    }

    @Test
    void testSetTaskCompleted() throws Exception {
        mockMvc.perform(patch("/rest/task/setCompleted")
                .param("id", "1"))
                .andExpect(status().is2xxSuccessful());
        verify(scheduleServiceMock, times(1)).updateTask((1L), (true));
    }

    //    4xx errors  indicate issues at clients end
    @Test
    void testSetTaskCompletedWithIncorrectId() throws Exception {
        String response = mockMvc.perform(patch("/rest/task/setCompleted"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResolvedException()
                .getMessage();
        assertTrue(response.contains("'id' is not present"));
        verifyNoInteractions(scheduleServiceMock);
    }

    @Test
    void testSetTaskNotCompleted() throws Exception {
        mockMvc.perform(patch("/rest/task/setNotCompleted")
                .param("id", "1"))
                .andExpect(status().is2xxSuccessful());
        verify(scheduleServiceMock, times(1)).updateTask((1L), (false));
    }

    @Test
    void testSetTaskNotCompletedWithIncorrectId() throws Exception {
        String response = mockMvc.perform(patch("/rest/task/setNotCompleted"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResolvedException()
                .getMessage();
        assertTrue(response.contains("'id' is not present"));
        verifyNoInteractions(scheduleServiceMock);
    }

    @Test
    void testDeleteTaskWithCorrectId() throws Exception {
        mockMvc.perform(delete("/rest/task").
                param("id", "1")).
                andExpect(status().is2xxSuccessful());
        verify(scheduleServiceMock, times(1)).deleteTask(1L);
    }

    @Test
    void testDeleteTaskNotCompletedWithIncorrectId() throws Exception {
        String response = mockMvc.perform(delete("/rest/task"))
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResolvedException()
                .getMessage();
        assertTrue(response.contains("'id' is not present"));
        verifyNoInteractions(scheduleServiceMock);
    }
}

