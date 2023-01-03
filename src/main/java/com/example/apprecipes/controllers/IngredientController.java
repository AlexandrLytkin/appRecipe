package com.example.apprecipes.controllers;

import com.example.apprecipes.model.Ingredient;
import com.example.apprecipes.exeptions.NotWrongArgument;
import com.example.apprecipes.model.Recipe;
import com.example.apprecipes.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @Operation(
            summary = "Получить все ингридиенты",
            description = "Параметры вводить не нужно"
    )
    public Collection<Ingredient> getAllIngredient() {
        return ingredientService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить один ингридиент по id",
            description = "Введите номер id ингридиента"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(mediaType = "application/json")
                    }),
            @ApiResponse(responseCode = "404", description = "Ингридиент не найден", content = {})
    })
    public Ingredient getIngredientById(@PathVariable("id") int id) throws NotWrongArgument {
        return this.ingredientService.getOne(id);
    }


    @PostMapping
    @Operation(
            summary = "Добавьте ингридиент",
            description = "используй способ добавления application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингридиенты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) throws NotWrongArgument {
        return this.ingredientService.add(ingredient);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновите ингридиент ",
            description = "Введите id ингридиента который хотите обновить и сам ингридиент application/json"
    )
    public Ingredient updateIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) throws NotWrongArgument {
        return this.ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удалить ингридиент ",
            description = "введите id ингридиента который хотите удалить"
    )
    public Ingredient removeIngredient(@PathVariable("id") int id) {
        return this.ingredientService.delete(id);
    }
}
