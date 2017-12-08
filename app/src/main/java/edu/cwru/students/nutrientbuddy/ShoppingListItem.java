package edu.cwru.students.nutrientbuddy;

import android.util.Log;

import java.io.Serializable;

public class ShoppingListItem implements Serializable{

    private String name;
    private String quantity;
    private String cost;
    private long id;

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

    public long getId(){
        return this.id;
    }

    public void setID(long uniqueID){
        this.id = uniqueID;
    }

}
