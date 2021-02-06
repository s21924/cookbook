package com.example.cookbookapp.model.projection;

import com.example.cookbookapp.model.Recipe;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupReadModel {
    private int id;
    private String description;


    private Set<RecipeReadModel> steps;

    public GroupReadModel(Recipe source) {
        id = source.getId();
        description = source.getDescription();

        steps = source.getSteps().stream()
                .map(RecipeReadModel::new)
                .collect(Collectors.toSet());
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }



    public Set<RecipeReadModel> getSteps() {
        return steps;
    }

    public void setSteps(final Set<RecipeReadModel> steps) {
        this.steps = steps;
    }
}
