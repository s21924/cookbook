package com.example.cookbookapp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StepRepository {
    List<Step> findAll();

    Page<Step> findAll(Pageable page);

    Optional<Step> findById(Integer id);

    boolean existsById(Integer id);

    boolean existsByDoneIsFalseAndRecipe_Id(Integer groupId);

    List<Step> findByDone(boolean done);

    Step save(Step entity);

    List<Step> findAllByRecipe_Id(Integer groupId);


}
