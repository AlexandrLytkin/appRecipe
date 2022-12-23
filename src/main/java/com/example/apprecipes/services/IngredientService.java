package com.example.apprecipes.services;

import com.example.apprecipes.model.Ingredient;
import com.example.apprecipes.model.NotWrongArgument;

import java.util.Collection;

public interface IngredientService {

    Collection<Ingredient> getAll();

    Ingredient add(Ingredient ingredient) throws NotWrongArgument;

    Ingredient getOne(int idIngredient) throws NotWrongArgument;

    Ingredient update(int id, Ingredient recipe) throws NotWrongArgument;

    Ingredient delete(int id);
}
