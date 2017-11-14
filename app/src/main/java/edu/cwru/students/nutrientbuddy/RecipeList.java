package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import android.util.Log;

public class RecipeList {

    private static final String TAG = "MyActivity";

    private ArrayList<Recipe> list;

    public RecipeList(){
        list = new ArrayList<Recipe>();
    }

    public void addItem(Recipe item){
        list.add(item);
    }

    public boolean removeItem(Recipe item){
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
        for(Recipe recipeItem : list){
            Log.v(TAG, recipeItem.getName());
        }
    }

    public ArrayList<String> getRecipeNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(Recipe recipeItem : list){
            names.add(recipeItem.getName());
        }
        return names;
    }

    public ArrayList<Recipe> getRecipeItems(){
        return list;
    }
}