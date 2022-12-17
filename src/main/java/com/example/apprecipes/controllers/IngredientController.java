package com.example.apprecipes.controllers;

import com.example.apprecipes.model.Ingredient;
import com.example.apprecipes.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public Collection<Ingredient> getAllIngredient() {
        return ingredientService.getAll();
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return this.ingredientService.addNewIngredient(ingredient);
    }
}
