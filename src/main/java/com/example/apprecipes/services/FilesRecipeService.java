package com.example.apprecipes.services;

public interface FilesRecipeService {

    boolean safeToFile(String json);

    String readFromFile();
}
