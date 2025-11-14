package com.whatsforcoocking.recipeservice.rest;

import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;
import com.whatsforcoocking.recipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("api/v1/recipes")
@Slf4j
@RequiredArgsConstructor
public class RecipesController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<?> createRecipe(RecipeDto recipeDto){
        log.trace("[RecipesController] Create recipe user: {}", "SET USER HERE");
        return ResponseEntity.ok(recipeService.createRecipe(recipeDto, UUID.randomUUID())); //toDo брать id из токена
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable UUID id){
        log.trace("[RecipesController] Get recipe id: {} user: {}", id, "SET USER HERE");
        return ResponseEntity.ok(recipeService.getRecipe(id, UUID.randomUUID())); //toDo брать id из токена
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable UUID id, RecipeDto recipeDto){
        log.trace("[RecipesController] Update recipe id: {} user: {}", id, "SET USER HERE");
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipeDto, UUID.randomUUID())); //toDo брать id из токена
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable UUID id){
        log.trace("[RecipesController] Delete recipe id: {} user: {}", id, "SET USER HERE");
        return ResponseEntity.ok(recipeService.deleteRecipe(id, UUID.randomUUID())); //toDo брать id из токена
    }
}
