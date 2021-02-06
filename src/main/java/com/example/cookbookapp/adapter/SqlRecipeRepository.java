package com.example.cookbookapp.adapter;

import com.example.cookbookapp.model.Recipe;
import com.example.cookbookapp.model.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
 interface SqlRecipeRepository extends RecipeRepository, JpaRepository<Recipe, Integer> {
   @Override
   @Query("select distinct r from Recipe r join fetch r.steps")
   List<Recipe> findAll();



}
