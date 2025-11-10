package com.whatsforcoocking.recipeservice.service.impl;

import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;
import com.whatsforcoocking.recipeservice.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DefaultRecipeService implements RecipeService {

    @Override
    public RecipeDto createRecipe(RecipeDto recipeDto) {
        return null;
    }

    @Override
    public RecipeDto getRecipe(UUID id) {
        return null;
    }

    @Override
    public UUID deleteRecipe(UUID id) {
        return null;
    }
}
