package com.example.apprecipes.services;

import com.example.apprecipes.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    Collection<Recipe> getAll();

    Recipe add(Recipe recipe);

    Recipe getOne(int idRecipe);

    Recipe update(int id, Recipe recipe);

    Recipe delete(int id);
}
