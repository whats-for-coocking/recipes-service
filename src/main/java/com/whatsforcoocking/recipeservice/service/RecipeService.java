package com.whatsforcoocking.recipeservice.service;


import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;

import java.util.UUID;

public interface RecipeService {

    RecipeDto createRecipe(RecipeDto recipeDto, UUID userid);

    RecipeDto getRecipe(UUID id, UUID userId);

    RecipeDto updateRecipe(UUID id, RecipeDto recipeDto, UUID userId);

    UUID deleteRecipe(UUID id, UUID userId);

}
