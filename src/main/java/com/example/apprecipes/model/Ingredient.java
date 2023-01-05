package com.example.apprecipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String name;
    private int count;
    private String measure;

    @Override
    public String toString() {
        return "\n" +
                "*" + name +
                " = " + count +
                " " + measure;
    }
}
