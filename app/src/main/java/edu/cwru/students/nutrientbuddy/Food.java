package edu.cwru.students.nutrientbuddy;

import java.util.HashMap;
import java.util.Map;

public class Food {

    private Map<String, String> data;

    //todo these should probs be enums
    public static String name = "item_name";
    public static String totalCalories = "nf_calories";
    public static String totalFat = "nf_total_fat";
    public static String sodium = "nf_sodium";
    public static String totalCarbs = "nf_total_carbohydrate";
    public static String totalSugar = "nf_sugars";
    public static String protein = "nf_protein";

    public Food(){
        this.data = new HashMap<String, String>();
    }

    public Food(String foodName, String cals, String fat, String sod, String carbs, String sugar, String protien){
        this();

        this.data.put(this.name,foodName);
        this.data.put(this.totalCalories, cals);
        this.data.put(this.totalFat, fat);
        this.data.put(this.sodium, sod);
        this.data.put(this.totalCarbs, carbs);
        this.data.put(this.totalSugar, sugar);
        this.data.put(this.protein, protien);
    }

    ///////////  Get Set for expansion ///////

    public void set(String nutritionalField, String value){
        this.data.put(nutritionalField, value);

    }

    public String get(String nutritionalField){
        return this.data.get(nutritionalField);
    }

    /////////// Getter Setters //////////// todo depreciate these

    public void setName(String foodName){
        this.data.put(this.name, foodName);
    }

    public void setTotalCalories(String calories){
        this.data.put(this.totalCalories, calories);
    }

    public void setTotalFat(String fat){
        this.data.put(this.totalFat, fat);
    }

    public void setSodium(String sodium){
        this.data.put(this.sodium, sodium);
    }

    public void setTotalCarbs(String carbs){
        this.data.put(this.totalCarbs, carbs);
    }

    public void setTotalSugar(String sugar){
        this.data.put(this.totalSugar, sugar);
    }

    public void setProtein(String protein){
        this.data.put(this.protein, protein);
    }

    public String getName(){
        return this.data.get(this.name);
    }

    public String getCalories(){
        return this.data.get(this.totalCalories);
    }

    public String getTotalFat(){
        return this.data.get(this.totalFat);
    }

    public String getSodium(){
        return this.data.get(this.sodium);
    }

    public String getCarbs(){
        return this.data.get(this.totalCarbs);
    }

    public String getSugar(){
        return this.data.get(this.totalSugar);
    }

    public String getProtein(){
        return this.data.get(this.protein);
    }

}