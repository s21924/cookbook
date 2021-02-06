package com.example.cookbookapp.model.projection;

import com.example.cookbookapp.model.Recipe;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GroupWriteModel {
    @NotBlank(message = "Step group's description must not be empty")
    private String description;
    @Valid
    private List<RecipeWriteModel> steps = new ArrayList<>();

    public GroupWriteModel() {
        steps.add(new RecipeWriteModel());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<RecipeWriteModel> getSteps() {
        return steps;
    }

    public void setSteps(final List<RecipeWriteModel> steps) {
        this.steps = steps;
    }

    public Recipe toGroup() {
        var result = new Recipe();
        result.setDescription(description);
        result.setSteps(
                steps.stream()
                        .map(source -> source.toStep(result))
                        .collect(Collectors.toSet())
        );

        return result;
    }
}