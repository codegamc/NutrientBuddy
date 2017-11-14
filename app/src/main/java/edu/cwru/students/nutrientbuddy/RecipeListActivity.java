package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
public class RecipeListActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    private RecipeList recipeList;
    private ArrayList<String> recipeNames;
    private ArrayList<Recipe> recipes;

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview = (ListView) findViewById(R.id.recipe_list);

        recipeList = new RecipeList();  // Currently will probably override whatever previous list we made.
        recipes = new ArrayList<Recipe>();

        recipeList.addItem(new Recipe("Name1", "Ingredients1", "Directions1"));
        recipeList.addItem(new Recipe("Name2", "Ingredients2", "Directions2"));
        recipeList.addItem(new Recipe("Name3", "Ingredients3", "Directions3"));

        recipeNames = recipeList.getRecipeNames();

        if(!recipeNames.isEmpty()) {
            Log.v(TAG, "Inside the statement");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) recipeNames);

            listview.setAdapter(arrayAdapter);
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(RecipeListActivity.this, ViewRecipeActivity.class);

                Log.v(TAG, "Selected position " + position);
                Log.v(TAG, "Selected food: " + recipeNames.get(position));
                Log.v(TAG, "Selected food from items: " + recipeList.getRecipeItems().get(position).getName());


                intent.putExtra("recipeName", recipeList.getRecipeItems().get(position).getName());
                intent.putExtra("recipeIngredients", recipeList.getRecipeItems().get(position).getIngredients());
                intent.putExtra("recipeDirections", recipeList.getRecipeItems().get(position).getDirections());

                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ADD A NEW RECIPE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void addRecipe(View view) {
        //startActivityForResult(new Intent(this, EditRecipeActivity.class), 0);

        Intent nextScreen = new Intent(getApplicationContext(), EditRecipeActivity.class);
        startActivityForResult(nextScreen,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        String recipeName = data.getStringExtra("recipeName");
        String recipeIngredients = data.getStringExtra("recipeIngredients");
        String recipeDirections = data.getStringExtra("recipeDirections");

        Log.v(TAG, "Recipe name found: " + recipeName);
        Log.v(TAG, "Recipe ingredients found: " + recipeIngredients);
        Log.v(TAG, "Recipe directions found: " + recipeDirections);

        recipeList.addItem(new Recipe(recipeName, recipeIngredients, recipeDirections));

        recipeNames = recipeList.getRecipeNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) recipeNames);

        listview.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_home :
                Intent intent = new Intent(RecipeListActivity.this, SearchScreenActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_shoppingList :
                Intent intent2 = new Intent(RecipeListActivity.this, ShoppingListActivity.class);
                startActivity(intent2);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }


}
