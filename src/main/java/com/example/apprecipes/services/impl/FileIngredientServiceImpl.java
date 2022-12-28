package com.example.apprecipes.services.impl;

import com.example.apprecipes.services.FileIngredientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileIngredientServiceImpl implements FileIngredientService {

    @Value("${path.to.data.file}")
    private String dataFilePathIngredients;

    @Value("${name.of.data.fileIngredients}")
    private String dataFileNameIngredients;

    @Override
    public boolean safeToFileIngredients(String json) {
        try {
            cleanDataFileIngredients();
            Files.writeString(Path.of(dataFilePathIngredients, dataFileNameIngredients),json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFileIngredients() {
        try {
            return Files.readString(Path.of(dataFilePathIngredients, dataFileNameIngredients));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFileIngredients() {
        try {
            Path path = Path.of(dataFilePathIngredients, dataFileNameIngredients);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
