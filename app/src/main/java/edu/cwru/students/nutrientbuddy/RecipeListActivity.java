package edu.cwru.students.nutrientbuddy;

import android.content.ContentValues;
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
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class RecipeListActivity extends AppCompatActivity {

    private static final String TAG = "RecipeListActivity";

    private RecipeList recipeList;
    private ArrayList<String> recipeNames;
    private ArrayList<Recipe> recipes;

    private DatabaseHelper recipeDatabaseHelper;

    ListView recipeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipeDatabaseHelper = new DatabaseHelper(this);



            // Testing purposes: clear the database:
          //   recipeDatabaseHelper.removeAll(DatabaseHelper.TABLE_USERS);





        ///////////////// UI Stuff
        // Recipe List View
        this.recipes = new ArrayList<Recipe>();
        this.recipeList = new RecipeList();
        this.recipeListView = (ListView) findViewById(R.id.recipe_list);


       /* if(false){ //todo use the savedUserPreferences to toggle a debug mode
            this.recipeList.addItem(new Recipe("Name1", "Ingredients1", "Directions1"));
            this.recipeList.addItem(new Recipe("Name2", "Ingredients2", "Directions2"));
            this.recipeList.addItem(new Recipe("Name3", "Ingredients3", "Directions3"));


        }*/

        // addNewRecipeDB(null, null, 0);
       // addNewRecipeDB("NAME!", "Ingredients1", "Directions1");
       // addNewRecipeDB("Name2", "Ingredients2", "Directions2");
       // addNewRecipeDB("Name3", "Ingredients3", "Directions3");




        Cursor c = recipeDatabaseHelper.query(DatabaseHelper.TABLE_USERS, DatabaseHelper.COL_NAME);


        while(c.moveToNext()){
            String col_name = c.getString(c.getColumnIndex("name"));
            String col_ingredients = c.getString(c.getColumnIndex("ingredients"));
            String col_directions = c.getString((c.getColumnIndex("directions")));

            Recipe r = new Recipe(col_name, col_ingredients, col_directions);

            recipeList.addItem(r);
        }

        /*String[] from = new String[]{DatabaseHelper.COL_NAME};
        int[] to = {android.R.id.text1};
        SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, from, to, 0);
        recipeListView.setAdapter(adapter1);
        */


        this.recipeNames = recipeList.getRecipeNames();

        if(!this.recipeNames.isEmpty()) {
            ArrayAdapter<String> recipeListAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) recipeNames);
            this.recipeListView.setAdapter(recipeListAdapter);
        }

        recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(RecipeListActivity.this, ViewRecipeActivity.class);

                intent.putExtra("recipeName",           recipeList.getRecipeItems().get(position).getName());
                intent.putExtra("recipeIngredients",    recipeList.getRecipeItems().get(position).getIngredients());
                intent.putExtra("recipeDirections",     recipeList.getRecipeItems().get(position).getDirections());

                startActivity(intent);
            }
        });


        //todo rename all this
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ADD A NEW RECIPE", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent nextScreen = new Intent(getApplicationContext(), EditRecipeActivity.class);
                startActivityForResult(nextScreen,2000);
            }
        });
    }

    public void addRecipe(View view) {
        //startActivityForResult(new Intent(this, EditRecipeActivity.class), 0);

        Intent nextScreen = new Intent(getApplicationContext(), EditRecipeActivity.class);
        startActivityForResult(nextScreen,2000);
    }

    private void addNewRecipeDB(String name, String ingredients, String directions){
        ContentValues values = new ContentValues();
        if(name != null) {
            values.put(DatabaseHelper.COL_NAME, name);
        }
        if(ingredients != null){
            values.put(DatabaseHelper.COL_INGREDIENTS, ingredients);
        }

        if(directions != null){
            values.put(DatabaseHelper.COL_DIRECTIONS, directions);
        }

        recipeDatabaseHelper.insert(DatabaseHelper.TABLE_USERS, values);

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

        addNewRecipeDB(recipeName, recipeIngredients, recipeDirections);

        this.recipeList.addItem(new Recipe(recipeName, recipeIngredients, recipeDirections));

        this.recipeNames = this.recipeList.getRecipeNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) recipeNames);

        this.recipeListView.setAdapter(arrayAdapter);

        /*Cursor c = recipeDatabaseHelper.query(DatabaseHelper.TABLE_USERS, DatabaseHelper.COL_NAME);
        String[] from = new String[]{DatabaseHelper.COL_NAME};
        int[] to = {android.R.id.text1};
        SimpleCursorAdapter adapter1 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, c, from, to, 0);
        recipeListView.setAdapter(adapter1);*/
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
