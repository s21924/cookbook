package com.example.cookbookapp.controller;


import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class StepControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StepRepository repo;

    @Test
    void httpGet_returnsGivenTask() throws Exception {
        //given
        int id = repo.save(new Step("foo")).getId();

        //when+then
        mockMvc.perform(get("/steps/" + id))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
