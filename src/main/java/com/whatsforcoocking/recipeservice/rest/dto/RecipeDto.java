package com.whatsforcoocking.recipeservice.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecipeDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
