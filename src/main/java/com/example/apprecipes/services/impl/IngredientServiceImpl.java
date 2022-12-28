package com.example.apprecipes.services.impl;

import com.example.apprecipes.model.Ingredient;
import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.services.IngredientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    public IngredientServiceImpl(FileIngredientServiceImpl ingredientFileService) {
        this.ingredientFileService = ingredientFileService;
    }

    @PostConstruct
    private void initialize() {
        readFromFile();
    }

    private final FileIngredientServiceImpl ingredientFileService;

    private static Map<Integer, Ingredient> ingredients = new HashMap<>();
    private static int id = 0;


    @Override
    public Collection<Ingredient> getAll() {
        return ingredients.values();
    }


    @Override
    public Ingredient add(Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            throw new NotWrongArgument("Не может добавить ингредиент с таким же id");
        } else {
            ingredients.put(id++, ingredient);
        }
        safeToFile();
        return ingredient;
    }

    @Override
    public Ingredient getOne(int id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new NotWrongArgument("Нет такого ингредиента");
        }
    }

    @Override
    public Ingredient update(int id, Ingredient recipe) {
        Ingredient currentIngredient = ingredients.get(id);
        if (currentIngredient == null) {
            throw new NotWrongArgument("Такого id рецепта нет!");
        }
        currentIngredient.setName(recipe.getName());
        currentIngredient.setCount(recipe.getCount());
        currentIngredient.setMeasure(recipe.getMeasure());
        safeToFile();
        return currentIngredient;
    }

    @Override
    public Ingredient delete(int id) {
        return ingredients.remove(id);
    }

    private void safeToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            ingredientFileService.safeToFileIngredients(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        try {
            String json = ingredientFileService.readFromFileIngredients();
            ingredients = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
