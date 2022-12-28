package com.example.apprecipes.services;

public interface FileIngredientService {

    boolean safeToFileIngredients(String json);

    String readFromFileIngredients();
}
