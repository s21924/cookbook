package com.example.cookbookapp.logic;


import com.example.cookbookapp.model.StepRepository;
import org.springframework.stereotype.Service;

@Service
public class StepService {

    private final StepRepository repository;

    public StepService(StepRepository repository) {
        this.repository = repository;
    }

}
