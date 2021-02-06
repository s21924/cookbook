package com.example.cookbookapp;

import com.example.cookbookapp.model.Step;
import com.example.cookbookapp.model.StepRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.*;

@Configuration
class TestConfiguration {
    @Bean
    @Primary
    @Profile("!integration")
    DataSource e2eTestDataSource() {
        var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }

    @Bean
    @Primary
    @Profile("integration")
    StepRepository testRepo() {
        return new StepRepository() {
            private Map<Integer, Step> tasks = new HashMap<>();

            @Override
            public List<Step> findAll() {
                return new ArrayList<>(tasks.values());
            }

            @Override
            public Page<Step> findAll(final Pageable page) {
                return null;
            }

            @Override
            public Optional<Step> findById(final Integer id) {
                return Optional.ofNullable(tasks.get(id));
            }

            @Override
            public boolean existsById(final Integer id) {
                return tasks.containsKey(id);
            }

            @Override
            public boolean existsByDoneIsFalseAndRecipe_Id(final Integer groupId) {
                return false;
            }

            @Override
            public List<Step> findByDone(final boolean done) {
                return null;
            }

            @Override
            public Step save(final Step entity) {
                int key = tasks.size() + 1;
                try {
                    var field = Step.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity, key);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                tasks.put(key, entity);
                return tasks.get(key);
            }

            @Override
            public List<Step> findAllByRecipe_Id(final Integer groupId) {
                return List.of();
            }
        };
    }
}

