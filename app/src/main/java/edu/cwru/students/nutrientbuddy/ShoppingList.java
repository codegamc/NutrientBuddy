package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import android.util.Log;

public class ShoppingList {

    private static final String TAG = "ShoppingList";

    private ArrayList<Food> list;

    public ShoppingList(){
        list = new ArrayList<Food>();
    }

    public void addItem(Food item){
        list.add(item);
    }

    public boolean removeItem(Food item){
        if(list.contains(item)){
            list.remove(item);
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<String> getItemNames(){
        ArrayList<String> array = new ArrayList<String>();
        for(Food item : list){
            array.add(item.getName());
        }
        return array;
    }

    public boolean clearList(){
        list.clear();
        return true;
    }

    public void printListContents(){
        for(Food foodItem : list){
            Log.v(TAG, foodItem.getName());
        }
    }

    public ArrayList<Food> getShopItems(){
        return this.list;
    }
}