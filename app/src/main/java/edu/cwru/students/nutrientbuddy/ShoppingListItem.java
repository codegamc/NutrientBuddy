package edu.cwru.students.nutrientbuddy;

import android.util.Log;

public class ShoppingListItem {

    private String name;
    private String quantity;
    private String cost;

    private static final String TAG = "SHOPPING_LIST_ITEM";

    public ShoppingListItem(String name, String quantity, String cost){
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    //////// GETTER SETTER METHODS ///////

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setQuantity(String quantity){
        this.quantity = quantity;
    }

    public String getQuantity(){
        return this.quantity;
    }

    public void setCost(String cost){
        this.cost = cost;
    }

    public String getCost(){
        return this.cost;
    }



}
