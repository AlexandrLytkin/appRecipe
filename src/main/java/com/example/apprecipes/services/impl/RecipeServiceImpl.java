package com.example.apprecipes.services.impl;

import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    private static Map<Integer, Recipe> recipes = new TreeMap<>();
    private static int id = 0;


    @Override
    public Collection<Recipe> getAll() {
        return recipes.values();
    }

    @Override
    public Recipe addNewRecipe(Recipe recipe) {
        if (recipes.containsKey(id)) {
            throw new RuntimeException("Не может добавить рецепт с таким же id");
        } else {
            recipes.put(id++, recipe);
        }
        return recipe;
    }

    @Override
    public Recipe getRecipeById(int id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }
}
