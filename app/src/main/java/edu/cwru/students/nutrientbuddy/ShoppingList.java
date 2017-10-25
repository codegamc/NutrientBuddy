package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import android.util.Log;

public class ShoppingList {

    private static final String TAG = "MyActivity";

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

    public boolean clearList(){
        list.clear();
        return true;
    }

    public void printListContents(){
        for(Food foodItem : list){
            Log.v(TAG, foodItem.getName());
        }
    }
}