package edu.cwru.students.nutrientbuddy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HelpActivity extends AppCompatActivity{

    private OpenItemsMenuHandler openItemsMenuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);


        TextView searchAnswer = (TextView) findViewById(R.id.search_answer);
        TextView recipeAnswer = (TextView) findViewById(R.id.recipe_answer);
        TextView shoppingAnswer = (TextView) findViewById(R.id.shopping_answer);
        TextView barcodeAnswer = (TextView) findViewById(R.id.barcode_answer);

        searchAnswer.setText("Use the menu bar to navigate to the Home page. Select the search bar and start typing " +
                "the name of a food you enjoy - like \"Apple\" and click on one of the results to view the nutritional content.");

        recipeAnswer.setText("Use the menu bar to navigate to the Recipes page. Click the Add Recipe button (+) to " +
                "navigate to the Recipe Editor. Fill in text for the name of the recipe, the ingredients needed, and the " +
                "instructions for how to make it! When you're finished, hit Done, and find your new recipe in the Recipe List. " +
                "To view the recipe details, select it from the list to see more information. You can also click the Delete button " +
                "to remove the recipe from your list.");

        shoppingAnswer.setText("Use the menu bar to navigate to the Shopping List page. Click the Add Item button (+) to " +
                "navigate to the Shopping List Editor. Fill in text for the name of the item, and optionally the estimated cost and " +
                "quantity! When you're finished, hit Done, and find your new item in your Shopping List. " +
                "To view the item details, select it from the list to see more information. You can also click the Delete button " +
                "to remove the item from your list.");

        barcodeAnswer.setText("Use the menu bar to navigate to the Barcode Scanning page. Click the Scan button to open " +
                "your phone's camera. Hold your camera in front of the food item's barcode - it should be a white image with black " +
                "lines. Then view the corresponding details as the app identifies the food and pulls up the nutrition facts!");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //////////////// Menu Bar Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_shoppingList:
                intent = new Intent(HelpActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(HelpActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(HelpActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(HelpActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
