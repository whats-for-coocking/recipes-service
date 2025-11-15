package com.whatsforcoocking.recipeservice.service.impl;

import com.whatsforcoocking.recipeservice.exception.NotAllowedException;
import com.whatsforcoocking.recipeservice.exception.ResourceNotFoundException;
import com.whatsforcoocking.recipeservice.mapper.RecipeMapper;
import com.whatsforcoocking.recipeservice.model.Recipe;
import com.whatsforcoocking.recipeservice.repository.RecipeRepository;
import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;
import com.whatsforcoocking.recipeservice.service.OutboxService;
import com.whatsforcoocking.recipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultRecipeService implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;
    private final OutboxService outboxService;

    @Override
    @Transactional
    public RecipeDto createRecipe(RecipeDto recipeDto, UUID userId) {
        log.trace("[DefaultRecipeService] Create recipe user: {}", userId);
        var recipeEntity = recipeMapper.toEntity(recipeDto);
        recipeEntity.setUserId(userId);
        var timestamp = Instant.now();
        recipeEntity.setCreated(timestamp);
        recipeEntity.setUpdated(timestamp);
        recipeEntity = recipeRepository.save(recipeEntity);
        outboxService.saveEvent(
                "RECIPE",
                recipeEntity.getId(),
                "RECIPE_CREATED",
                Map.of(
                        "id", recipeEntity.getId(),
                        "userId", userId,
                        "title", recipeEntity.getName(),
                        "createdAt", recipeEntity.getCreated()
                )
        );
        log.info("[DefaultRecipeService] Recipe created {} user: {}", recipeEntity.getId(), userId);
        return recipeMapper.toDto(recipeEntity);
    }

    @Override
    public RecipeDto getRecipe(UUID id, UUID userId) {
        log.trace("[DefaultRecipeService] Get recipe user: {}", userId);
        var recipeEntity = getOrThrow(id);
        log.info("[DefaultRecipeService] Recipe get {} user: {}", recipeEntity.getId(), userId);
        return recipeMapper.toDto(recipeEntity);
    }

    @Override
    public RecipeDto updateRecipe(UUID id, RecipeDto recipeDto, UUID userId) {
        log.trace("[DefaultRecipeService] Update recipe {} user: {}", id, userId);

        var existingRecipe = getOrThrow(id);

        if (!userId.equals(existingRecipe.getUserId())) {
            throw new NotAllowedException(
                    String.format("Update recipe %s not allowed for user %s", id, userId));
        }

        recipeMapper.updateEntityFromDto(recipeDto, existingRecipe);
        var updatedRecipe = recipeRepository.save(existingRecipe);

        log.info("[DefaultRecipeService] Recipe updated {} user: {}", id, userId);
        return recipeMapper.toDto(updatedRecipe);
    }

    @Override
    public UUID deleteRecipe(UUID id, UUID userId) {
        log.trace("[DefaultRecipeService] Delete recipe user: {}", userId);
        var recipeEntity = getOrThrow(id);
        if (!userId.equals(recipeEntity.getUserId()))
            throw new NotAllowedException(String.format("Delete recipe %s not allowed for user %s", recipeEntity.getId(), userId));
        recipeRepository.delete(recipeEntity);
        log.info("[DefaultRecipeService] Recipe deleted {} user: {}", recipeEntity.getId(), userId);
        return recipeEntity.getId();
    }

    private Recipe getOrThrow(UUID id){
        return recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe not found " + id));
    }
}
