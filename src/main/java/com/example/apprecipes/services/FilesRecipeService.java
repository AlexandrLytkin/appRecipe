package com.example.apprecipes.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesRecipeService {

    boolean safeToFile(String json);

    String readFromFile();

    File getDataFile();

    Path createTempFile(String suffix);

    boolean cleanDataFile();
}
