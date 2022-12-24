package com.example.apprecipes.services.impl;

import com.example.apprecipes.model.NotWrongArgument;
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
    public Recipe add(Recipe recipe) {
        if (recipes.containsKey(id)) {
            throw new NotWrongArgument("Не может добавить рецепт с таким же id");
        } else {
            recipes.put(id++, recipe);
        }
        return recipe;
    }

    @Override
    public Recipe getOne(int id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else {
            throw new NotWrongArgument("Нет такого рецепта");
        }
    }

    @Override
    public Recipe update(int id, Recipe recipe) {
        Recipe currentRecipe = recipes.get(id);
        if (currentRecipe == null) {
            throw new NotWrongArgument("Такого id рецепта нет!");
        }
        currentRecipe.setName(recipe.getName());
        currentRecipe.setIngredients(recipe.getIngredients());
        currentRecipe.setSteps(recipe.getSteps());
        currentRecipe.setTimeOfCook(recipe.getTimeOfCook());
        return currentRecipe;
    }

    @Override
    public Recipe delete(int id) {
        return recipes.remove(id);
    }

}
