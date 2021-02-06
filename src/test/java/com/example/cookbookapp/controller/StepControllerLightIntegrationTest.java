package com.example.cookbookapp.controller;


import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(StepController.class)
public class StepControllerLightIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    @Autowired
    private StepRepository repo;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        String description = "foo";
        when(repo.findById(anyInt()))
                .thenReturn(Optional.of( new Step(description)));
        //when+then
        mockMvc.perform(get("/steps/123"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.content().string(containsString(description)));
    }
}
