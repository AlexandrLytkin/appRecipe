package com.example.apprecipes.services;

import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    Collection<Recipe> getAll();

    Recipe add(Recipe recipe) throws NotWrongArgument;

    Recipe getOne(int idRecipe) throws NotWrongArgument;

    Recipe update(int id, Recipe recipe) throws NotWrongArgument;

    Recipe delete(int id);
}
