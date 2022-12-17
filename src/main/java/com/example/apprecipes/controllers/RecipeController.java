package com.example.apprecipes.controllers;

import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping
    public Collection<Recipe> getAllRecipe() {
        return recipeService.getAll();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addNewRecipe(recipe);
    }

}
