package com.example.apprecipes.services.impl;

import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.FilesRecipeService;
import com.example.apprecipes.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    public RecipeServiceImpl(FilesRecipeService filesRecipeService) {
        this.filesRecipeService = filesRecipeService;
    }

    @PostConstruct
    private void initialize() {
        readFromFile();
    }

    private final FilesRecipeService filesRecipeService;

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
        safeToFile();
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
        safeToFile();
        return currentRecipe;
    }

    @Override
    public Recipe delete(int id) {
        return recipes.remove(id);
    }

    private void safeToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesRecipeService.safeToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesRecipeService.readFromFile();
            recipes = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer,Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
