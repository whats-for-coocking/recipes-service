package com.whatsforcoocking.recipeservice.rest;

import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;
import com.whatsforcoocking.recipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipes")
@Slf4j
@RequiredArgsConstructor
public class RecipesController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody RecipeDto recipeDto,
                                          @RequestHeader("X-User-Id") UUID userId){
        log.trace("[RecipesController] Create recipe user: {}", userId.toString());
        return ResponseEntity.ok(recipeService.createRecipe(recipeDto, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable UUID id,
                                       @RequestHeader("X-User-Id") UUID userId){
        log.trace("[RecipesController] Get recipe id: {} user: {}", id, userId.toString());
        return ResponseEntity.ok(recipeService.getRecipe(id, userId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable UUID id, @RequestBody RecipeDto recipeDto,
                                          @RequestHeader("X-User-Id") UUID userId){
        log.trace("[RecipesController] Update recipe id: {} user: {}", id, userId.toString());
        return ResponseEntity.ok(recipeService.updateRecipe(id, recipeDto, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable UUID id,
                                          @RequestHeader("X-User-Id") UUID userId){
        log.trace("[RecipesController] Delete recipe id: {} user: {}", id, userId.toString());
        return ResponseEntity.ok(recipeService.deleteRecipe(id, userId));
    }
}
