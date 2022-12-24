package com.example.apprecipes.services;

import com.example.apprecipes.model.Ingredient;

import java.util.Collection;

public interface IngredientService {

    Collection<Ingredient> getAll();

    Ingredient add(Ingredient ingredient);

    Ingredient getOne(int idIngredient);

    Ingredient update(int id, Ingredient recipe);

    Ingredient delete(int id);
}
