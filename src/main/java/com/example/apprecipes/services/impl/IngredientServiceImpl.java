package com.example.apprecipes.services.impl;

import com.example.apprecipes.model.Ingredient;
import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

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
        return currentIngredient;
    }

    @Override
    public Ingredient delete(int id) {
        return ingredients.remove(id);
    }

}
