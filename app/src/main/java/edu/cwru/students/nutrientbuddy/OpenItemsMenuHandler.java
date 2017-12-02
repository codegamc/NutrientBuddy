package edu.cwru.students.nutrientbuddy;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.MenuItem;

/**
 * Created by michael on 11/19/17.
 */

public class OpenItemsMenuHandler {

    private AppCompatActivity activity;

    public OpenItemsMenuHandler(AppCompatActivity activity){
        this.activity = activity;
    }

    public boolean onOptionsItemSelected(int id) {
        switch(id) {
            case R.id.action_home :
                Intent intent = new Intent(this.activity, SearchScreenActivity.class);
                activity.startActivity(intent);
                return true;
            case R.id.action_recipe :
                Intent intent2 = new Intent(this.activity, RecipeListActivity.class);
                activity.startActivity(intent2);
                return true;
            case R.id.action_shoppingList :
                Intent intent3 = new Intent(this.activity, ShoppingListActivity.class);
                activity.startActivity(intent3);
                return true;
            default: return false;
        }
    }

}
