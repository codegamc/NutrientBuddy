package edu.cwru.students.nutrientbuddy;

public class Food {

    private String name,
            total_calories,
            total_fat,
            sodium,
            total_carbs,
            total_sugar,
            protein;

    public Food(){
        name = "";
        total_calories = "";
        total_fat = "";
        sodium = "";
        total_carbs = "";
        total_sugar = "";
        protein = "";
    }

    public Food(String foodName, String cals, String fat, String sod, String carbs, String sugar, String prot){

        name = foodName;
        total_calories = cals;
        total_fat = fat;
        sodium = sod;
        total_carbs = carbs;
        total_sugar = sugar;
        protein = prot;
    }

    public void setName(String foodName){
        name = foodName;
    }
    public void setTotalCalories(String calories){
        total_calories = calories;
    }
    public void setTotalFat(String fat){
        total_fat = fat;
    }
    public void setSodium(String sodiumAmt){
        sodium = sodiumAmt;
    }
    public void setTotalCarbs(String carbs){
        total_carbs = carbs;
    }
    public void setTotalSugar(String sugar){
        total_sugar = sugar;
    }
    public void setProtein(String proteinAmt){
        protein = proteinAmt;
    }

    public String getName(){
        return name;
    }
    public String getCalories(){
        return total_calories;
    }

    public String getTotalFat(){
        return total_fat;
    }

    public String getSodium(){
        return sodium;
    }
    public String getCarbs(){
        return total_carbs;
    }
    public String getSugar(){
        return total_sugar;
    }
    public String getProtein(){
        return protein;
    }

}