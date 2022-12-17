package com.example.apprecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private final String name;
    private int quantity;
    private String measure;
}
