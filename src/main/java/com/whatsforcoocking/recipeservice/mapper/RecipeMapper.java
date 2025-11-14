package com.whatsforcoocking.recipeservice.mapper;

import com.whatsforcoocking.recipeservice.model.Recipe;
import com.whatsforcoocking.recipeservice.rest.dto.RecipeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    @Mapping(target = "id", ignore = true)
    Recipe toEntity(RecipeDto dto);

    RecipeDto toDto(Recipe recipe);

    void updateEntityFromDto(RecipeDto recipeDto, @MappingTarget Recipe recipe);
}
