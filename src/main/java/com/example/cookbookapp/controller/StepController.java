package com.example.cookbookapp.controller;

import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/steps")
class StepController {
  private final StepRepository repository;

    StepController(final StepRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    ResponseEntity<Step> createStep(@RequestBody @Valid Step toCreate) {
        Step result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }



    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Step>> readAllSteps() {

        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping
    ResponseEntity<List<Step>> readAllSteps(Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/{id}")
    ResponseEntity<Step> readStep(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/done")
    ResponseEntity<List<Step>> readDoneSteps(@RequestParam(defaultValue = "true") boolean state) {
        return ResponseEntity.ok(
                repository.findByDone(state)
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateStep(@PathVariable int id, @RequestBody @Valid Step toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(step -> {
                    step.updateFrom(toUpdate);
                    repository.save(step);
                });
        return ResponseEntity.noContent().build();
    }



    @PatchMapping("/{id}")
    public ResponseEntity<?> toggleStep(@PathVariable int id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id)
                .ifPresent(step -> step.setDone(!step.isDone()));
        return ResponseEntity.noContent().build();
    }
}
