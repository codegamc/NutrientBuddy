package edu.cwru.students.nutrientbuddy;

import android.util.Log;

public class Recipe {

    private String name;
    private String ingredients;
    private String directions;

    private static final String TAG = " RECIPE";

    public Recipe(String name, String ingredients, String directions){
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
    }

    public void printRecipe(){

        Log.v(TAG, "Recipe name: " + name);
        Log.v(TAG, "Recipe ingredients: " + ingredients);
        Log.v(TAG, "Recipe directions: " + directions);

    }

    //////// GETTER SETTER METHODS ///////

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }

    public String getIngredients(){
        return this.ingredients;
    }

    public void setDirections(String directions){
        this.directions = directions;
    }

    public String getDirections(){
        return this.directions;
    }



}
