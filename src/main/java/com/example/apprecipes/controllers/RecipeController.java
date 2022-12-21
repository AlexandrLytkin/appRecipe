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

    @GetMapping("/{id}")
    public Recipe getRecipeById(@PathVariable("id") int id) {
        return this.recipeService.getRecipeById(id);
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addNewRecipe(recipe);
    }

    @PutMapping("/{id}")
    public Recipe updateRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) {
        return this.recipeService.updateRecipe(id, recipe);
    }

    @DeleteMapping("/{id}")
    public Recipe removeRecipe(@PathVariable("id") int id) {
        return this.recipeService.removeRecipe(id);
    }


}