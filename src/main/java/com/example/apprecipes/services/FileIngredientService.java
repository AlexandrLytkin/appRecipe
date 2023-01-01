package com.example.apprecipes.services;

import java.io.File;
import java.nio.file.Path;

public interface FileIngredientService {

    boolean safeToFileIngredients(String json);

    String readFromFileIngredients();

    File getDataFile();

    Path createTempFile(String suffix);

    boolean cleanDataFileIngredients();
}
