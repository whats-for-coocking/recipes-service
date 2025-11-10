package com.whatsforcoocking.recipeservice.service;


import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;

import java.util.UUID;

public interface RecipeService {

    RecipeDto createRecipe(RecipeDto recipeDto);

    RecipeDto getRecipe(UUID id);

    UUID deleteRecipe(UUID id);

}
