package com.example.cookbookapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "steps")
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Step's description must not be empty")
    private String description;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    Step() {
    }

    public Step(String description) {
        this(description, null);
    }

    public Step(String description, Recipe recipe) {
        this.description = description;
        if (recipe != null) {
            this.recipe = recipe;
        }
    }



    public int getId() {
        return id;
    }

    void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(final String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }


    public void updateFrom(final Step source) {
        description = source.description;
        done = source.done;
        recipe = source.recipe;
    }

    public void deleteFrom(final Step source) {
        description = source.description;
        done = source.done;
        recipe = source.recipe;
    }
}
