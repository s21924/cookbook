package com.example.cookbookapp.logic;


import com.example.cookbookapp.model.Recipe;
import com.example.cookbookapp.model.RecipeRepository;
import com.example.cookbookapp.model.StepRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecipeServiceTest {


    @Test
    @DisplayName("should throw when no recipe")
    void toggleRecipe_wrongId_throwsIllegalArgumentException() {
        // given
        StepRepository mockStepRepository = stepRepositoryReturning(false);
        // and
        var mockRepository = mock(RecipeRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
        // system under test
        var toTest = new RecipeService(mockRepository, mockStepRepository);

        // when
        var exception = catchThrowable(() -> toTest.toggleRecipe(1));

        // then
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("id not found");
    }

    @Test
    @DisplayName("should toggle done recipe")
    void toggleDoneRecipe_worksAsExpected() {
        // given
        StepRepository mockStepRepository = stepRepositoryReturning(false);
        // and
        var recipe = new Recipe();
        var beforeToggle = recipe.isDone();
        // and
        var mockRepository = mock(RecipeRepository.class);
        when(mockRepository.findById(anyInt())).thenReturn(Optional.of(recipe));
        // system under test
        var toTest = new RecipeService(mockRepository, mockStepRepository);

        // when
        toTest.toggleRecipe(0);

        // then
        assertThat(recipe.isDone()).isEqualTo(!beforeToggle);
    }

    private StepRepository stepRepositoryReturning(final boolean result) {
        var mockStepRepository = mock(StepRepository.class);
        when(mockStepRepository.existsByDoneIsFalseAndRecipe_Id(anyInt())).thenReturn(result);
        return mockStepRepository;
    }
}
