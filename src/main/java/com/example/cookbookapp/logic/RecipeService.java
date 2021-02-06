package com.example.cookbookapp.logic;

import com.example.cookbookapp.model.Recipe;
import com.example.cookbookapp.model.RecipeRepository;
import com.example.cookbookapp.model.StepRepository;
import com.example.cookbookapp.model.projection.GroupReadModel;
import com.example.cookbookapp.model.projection.GroupWriteModel;

import java.util.List;
import java.util.stream.Collectors;


public class RecipeService {
    private RecipeRepository repository;
    private StepRepository stepRepository;

    RecipeService(final RecipeRepository repository, final StepRepository stepRepository) {
        this.repository = repository;
        this.stepRepository = stepRepository;
    }


   public GroupReadModel createRecipe(final GroupWriteModel source) {
        Recipe result = repository.save(source.toGroup());
        return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll() {
        return repository.findAll().stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleRecipe(int recipeId) {
        if (stepRepository.existsByDoneIsFalseAndRecipe_Id(recipeId)) {
            throw new IllegalStateException("Group has undone steps. Done all the steps first");
        }
        Recipe result = repository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe with given id not found"));
        result.setDone(!result.isDone());
        repository.save(result);
    }
}
