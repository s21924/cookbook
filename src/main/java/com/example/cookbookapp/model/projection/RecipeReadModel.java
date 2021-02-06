package com.example.cookbookapp.model.projection;

import com.example.cookbookapp.model.Step;

public class RecipeReadModel {
    private String description;
    private boolean done;

    RecipeReadModel(Step source) {
        description = source.getDescription();
        done = source.isDone();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }
}
