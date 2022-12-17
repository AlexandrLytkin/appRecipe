package com.example.apprecipes.services;

import com.example.apprecipes.model.Recipe;

import java.util.Collection;

public interface RecipeService {


    Collection<Recipe> getAll();


    Recipe addNewRecipe(Recipe recipe);


    Recipe getRecipeById(int idRecipe);
}
