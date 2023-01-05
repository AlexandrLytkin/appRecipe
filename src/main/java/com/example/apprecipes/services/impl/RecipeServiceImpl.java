package com.example.apprecipes.services.impl;

import com.example.apprecipes.exeptions.NotWrongArgument;
import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.FilesRecipeService;
import com.example.apprecipes.services.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    public RecipeServiceImpl(FilesRecipeService filesRecipeService) {
        this.filesRecipeService = filesRecipeService;
    }

    @PostConstruct
    private void initialize() {
        try {
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public Path createRecipeReport() throws IOException {  //  ветка 6 метод ?
        Path path = filesRecipeService.createTempFile("все рецепты");
        for (Recipe recipe : recipes.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append("Название рецепта: " + recipe.getName() + "\nВремя приготовления " + recipe.getTimeOfCook() + ": мин\nИнгредиенты: " + recipe.getIngredients() + ": \nШаги приготовления:\n" + recipe.getSteps());
                writer.append("\n");
            }
        }
        return path;
    }

    @Override
    public void addRecipeFromInputStream(InputStream inputStream) throws IOException { // ветка 6 метод ?

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
                Recipe recipe = new Recipe(array[0], Integer.valueOf(array[1]), new ArrayList<>(), new ArrayList<>());
                add(recipe);
            }
        }
    }

    private void safeToFile() {
        try {
            DataFile dataFile = new DataFile(id, (TreeMap<Integer, Recipe>) recipes);
            String json = new ObjectMapper().writeValueAsString(dataFile);
            filesRecipeService.safeToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = filesRecipeService.readFromFile();
            DataFile dataFile = new ObjectMapper().readValue(json, new TypeReference<DataFile>() {
            });
            id = dataFile.getId();
            recipes = dataFile.getRecipes();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataFile {

        private int id;
        private TreeMap<Integer, Recipe> recipes;
    }
}
