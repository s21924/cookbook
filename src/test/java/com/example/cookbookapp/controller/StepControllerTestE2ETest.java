package com.example.cookbookapp.controller;

import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StepControllerTestE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    StepRepository repo;

    @Test
    void httpGet_returnsAllTasks() {
        //given
        int initial = repo.findAll().size();
        repo.save(new Step("test1"));
        repo.save(new Step("test2"));


        //when
        Step[] result = restTemplate.getForObject("http://localhost:" + port + "/steps", Step[].class);

        //then
        assertThat(result).hasSize(initial+2);


    }

}