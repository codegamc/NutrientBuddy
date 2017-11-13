package edu.cwru.students.nutrientbuddy;

import android.util.Log;

public class Recipe {

    private String name;
    private String ingredients;
    private String directions;

    private static final String TAG = "MyActivity";

    public Recipe(String n, String ing, String dir){
        name = n;
        ingredients = ing;
        directions = dir;
    }

    public void setName(String n){
        name = n;
    }

    public void setIngredients(String ing){
        ingredients = ing;
    }

    public void setDirections(String dir){
        directions = dir;
    }

    public String getName(){
        return name;
    }

    public String getIngredients(){
        return ingredients;
    }

    public String getDirections(){
        return directions;
    }

    public void printRecipe(){

        Log.v(TAG, "Recipe name: " + name);
        Log.v(TAG, "Recipe ingredients: " + ingredients);
        Log.v(TAG, "Recipe directions: " + directions);

    }

}
