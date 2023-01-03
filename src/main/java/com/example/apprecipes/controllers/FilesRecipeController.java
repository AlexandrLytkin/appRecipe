package com.example.apprecipes.controllers;

import com.example.apprecipes.services.FilesRecipeService;
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
public class FilesRecipeController {

    private final FilesRecipeService filesRecipeService;

    public FilesRecipeController(FilesRecipeService filesRecipeService) {
        this.filesRecipeService = filesRecipeService;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> downloadDataFIle() throws FileNotFoundException { // для загрузки файла
        File file = filesRecipeService.getDataFile();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFile(@RequestParam MultipartFile file) { // 1 получаем файл MultipartFile file
        filesRecipeService.cleanDataFile(); // 2 удаляем дата файл, создаем новый
        File dataFile = filesRecipeService.getDataFile(); // 3 берем про него инфо

        try (FileOutputStream fos = new FileOutputStream(dataFile)) { // 4 открываем исходящий поток

            IOUtils.copy(file.getInputStream(), fos); // 5 берем исходящий поток из параметров запроса @RequestParam MultipartFile file копируем его в исходящий запрос, копируем его в входящий поток fos
            return ResponseEntity.ok().build(); // 6 если все успешно отправляем ок
        } catch (IOException e) { // 7 если ошибка
            e.printStackTrace(); // 8 -//-
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 9 отправляем что пошло не так
    }
}