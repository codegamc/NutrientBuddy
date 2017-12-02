package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import android.util.Log;

public class RecipeList {

    private static final String TAG = "RecipeList";

    private ArrayList<Recipe> list;

    public RecipeList(){
        this.list = new ArrayList<Recipe>();
    }

    public void addItem(Recipe recipe){
        this.list.add(recipe);
    }

    public boolean removeRecipe(Recipe recipe){
        if(this.list.contains(recipe)){
            this.list.remove(recipe);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean clearList(){
        this.list.clear();
        //there is no need to give it a return value if it *always* returns true
        return true;
    }

    public ArrayList<String> getRecipeNames(){
        ArrayList<String> names = new ArrayList<String>();
        for(Recipe recipeItem : this.list){
            names.add(recipeItem.getName());
        }
        return names;
    }

    public ArrayList<Recipe> getRecipeItems(){
        return this.list;
    }
}