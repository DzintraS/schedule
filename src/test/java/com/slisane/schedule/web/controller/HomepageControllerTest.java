package com.slisane.schedule.web.controller;

import com.slisane.schedule.rest.controller.ScheduleController;
import com.slisane.schedule.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomepageController.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
 class HomepageControllerTest {

        @Autowired
        MockMvc mockMvc;
        @MockBean
        private ScheduleController scheduleControllerMock;
        @InjectMocks
        private HomepageController homepageController;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHomepage() throws Exception{
//        TODO how to test web controller?
    }
}
