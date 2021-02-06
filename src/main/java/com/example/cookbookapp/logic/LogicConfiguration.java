package com.example.cookbookapp.logic;

import com.example.cookbookapp.model.RecipeRepository;
import com.example.cookbookapp.model.StepRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {


    @Bean
    RecipeService recipeService(
        final RecipeRepository recipeRepository,
        final StepRepository stepRepository
    ){
        return new RecipeService(recipeRepository, stepRepository);
    }



}
