package com.example.cookbookapp.adapter;

import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SqlStepRepository extends StepRepository, JpaRepository<Step, Integer> {

    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from steps where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByDoneIsFalseAndRecipe_Id(Integer recipeId);

    @Override
    List<Step> findAllByRecipe_Id(Integer groupId);
}
