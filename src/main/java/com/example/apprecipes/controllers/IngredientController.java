package com.example.apprecipes.controllers;

import com.example.apprecipes.model.Ingredient;
import com.example.apprecipes.model.NotWrongArgument;
import com.example.apprecipes.services.IngredientService;
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
    public Collection<Ingredient> getAllIngredient() {
        return ingredientService.getAll();
    }

    @GetMapping("/{id}")
    public Ingredient getIngredientById(@PathVariable("id") int id) throws NotWrongArgument {
        return this.ingredientService.getOne(id);
    }

    @PostMapping
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) throws NotWrongArgument {
        return this.ingredientService.add(ingredient);
    }

    @PutMapping("/{id}")
    public Ingredient updateIngredient(@PathVariable("id") int id, @RequestBody Ingredient ingredient) throws NotWrongArgument {
        return this.ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    public Ingredient removeIngredient(@PathVariable("id") int id) {
        return this.ingredientService.delete(id);
    }
}
