package com.example.apprecipes.controllers;

import com.example.apprecipes.exeptions.NotWrongArgument;
import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD операции и другие эндпоинты для работы с рецептами")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(
            summary = "Получить все рецепты",
            description = "Параметры вводить не нужно"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Collection<Recipe> getAllRecipe() {
        return recipeService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить один рецепт по id",
            description = "Введите номер id рецепта"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json")
                    }),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден", content = {})
    })
    public Recipe getRecipeById(@PathVariable("id") int id) throws NotWrongArgument {
        return this.recipeService.getOne(id);
    }

    @PostMapping
    @Operation(
            summary = "Добавьте рецепт",
            description = "используй способ добавления application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe addRecipe(@RequestBody Recipe recipe) throws NotWrongArgument {
        return this.recipeService.add(recipe);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновите рецепт ",
            description = "Введите id рецепта который хотите обновить и сам рецепт application/json"
    )
    public Recipe updateRecipe(@PathVariable("id") int id, @RequestBody Recipe recipe) throws NotWrongArgument {
        return this.recipeService.update(id, recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить рецепт ",
            description = "введите id рецепта который хотите удалить"
    )
    public Recipe removeRecipe(@PathVariable("id") int id) {
        return this.recipeService.delete(id);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // ветка 6 метод ?
    public ResponseEntity<Object> addRecipeFromFile(@RequestParam MultipartFile file) {
        try (InputStream stream = file.getInputStream()){
            recipeService.addRecipeFromInputStream(stream);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
