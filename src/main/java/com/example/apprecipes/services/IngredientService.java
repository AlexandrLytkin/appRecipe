package com.example.apprecipes.services;

import com.example.apprecipes.model.Ingredient;

import java.util.Collection;

public interface IngredientService {

    Collection<Ingredient> getAll();

    Ingredient addNewIngredient(Ingredient ingredient);

    Ingredient getIngredientById(int idIngredient);

    Ingredient updateIngredient(int id, Ingredient recipe);

    Ingredient removeIngredient(int id);
}
