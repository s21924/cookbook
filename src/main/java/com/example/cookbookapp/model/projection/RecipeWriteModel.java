package com.example.cookbookapp.model.projection;

import com.example.cookbookapp.model.Recipe;
import com.example.cookbookapp.model.Step;

import javax.validation.constraints.NotBlank;

public class RecipeWriteModel {
    @NotBlank(message = "Step's description must not be empty")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }





    Step toStep(final Recipe recipe) {
        return new Step(description, recipe);
    }
}
