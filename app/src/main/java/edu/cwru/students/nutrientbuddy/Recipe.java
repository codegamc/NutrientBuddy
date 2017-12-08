package edu.cwru.students.nutrientbuddy;

import android.util.Log;

import java.io.Serializable;

public class Recipe implements Serializable{

    private String name;
    private String ingredients;
    private String directions;
    private long id;

    private static final String TAG = " RECIPE";

    public Recipe(String name, String ingredients, String directions){
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
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

    public long getId(){
        return this.id;
    }
    public void setID(long uniqueID){
        this.id = uniqueID;
    }



}
