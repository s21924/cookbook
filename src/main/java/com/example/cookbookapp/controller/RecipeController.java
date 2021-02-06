package com.example.cookbookapp.controller;

import com.example.cookbookapp.logic.RecipeService;
import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import com.example.cookbookapp.model.projection.GroupReadModel;
import com.example.cookbookapp.model.projection.GroupWriteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@Controller
@RequestMapping("/recipes")
class RecipeController {
    private final RecipeService recipeService;
    private final StepRepository repository;

    RecipeController(final RecipeService recipeService, final StepRepository repository) {
        this.recipeService = recipeService;
        this.repository = repository;
    }



    @PostMapping
    ResponseEntity<GroupReadModel> createRecipe(@RequestBody @Valid GroupWriteModel toCreate) {
        GroupReadModel result = recipeService.createRecipe(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @GetMapping
    ResponseEntity<List<GroupReadModel>> readAllRecipes() {
        return ResponseEntity.ok(recipeService.readAll());
    }


    @GetMapping(value = "/{id}")
    ResponseEntity<List<Step>> readAllStepsFromRecipe(@PathVariable int id) {
        return ResponseEntity.ok(repository.findAllByRecipe_Id(id));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> toggleRecipe(@PathVariable int id) {
        recipeService.toggleRecipe(id);
        return ResponseEntity.noContent().build();
    }



    @ModelAttribute("groups")
    List<GroupReadModel> getRecipe() {
        return recipeService.readAll();
    }
}
