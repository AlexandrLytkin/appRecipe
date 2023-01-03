package com.example.apprecipes.controllers;

import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.services.FileIngredientService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
@RequestMapping("/files")
public class FilesIngredientController {

    public final FileIngredientService fileIngredientService;

    public FilesIngredientController(FileIngredientService fileIngredientService) {
        this.fileIngredientService = fileIngredientService;
    }

    @GetMapping("/export/ingredient")
    public ResponseEntity<InputStreamResource> downloadDataFile() throws FileNotFoundException {
        File fileIng = fileIngredientService.getDataFile();

        if (fileIng.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileIng));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(fileIng.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"IngredientLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) {
        fileIngredientService.cleanDataFileIngredients();
        File dataFileING = fileIngredientService.getDataFile();

        try (FileOutputStream fos = new FileOutputStream(dataFileING)) {

            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new NotWrongArgument(e);
        }
    }
}
