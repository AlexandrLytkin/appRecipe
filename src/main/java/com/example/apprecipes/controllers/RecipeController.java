package com.example.apprecipes.controllers;

import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD операции и другие эндпоинты для работы с рецептами")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping

    public Collection<Recipe> getAllRecipe() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}")

    public Recipe getRecipeById(@PathVariable("id") int id) throws NotWrongArgument {
        return this.recipeService.getOne(id);
    }

    @PostMapping

    public Recipe addRecipe(@RequestBody Recipe recipe) throws NotWrongArgument {
        return this.recipeService.add(recipe);
    }

    @PutMapping("/{id}")

    public Recipe updateRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) throws NotWrongArgument {
        return this.recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")

    public Recipe removeRecipe(@PathVariable("id") int id) {
        return this.recipeService.delete(id);
    }


}
