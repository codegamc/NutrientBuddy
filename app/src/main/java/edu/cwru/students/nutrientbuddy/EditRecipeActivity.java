package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.app.Activity;
import android.view.View;

public class EditRecipeActivity extends AppCompatActivity {

    private static final String TAG = "EditRecipeActivity";

    private EditText recipeNameText;
    private EditText recipeIngredientsText;
    private EditText recipeDirectionsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //////////////////////
        // Collecting the Views
        this.recipeNameText = (EditText) findViewById(R.id.recipeNameEdit);
        this.recipeIngredientsText = (EditText) findViewById(R.id.ingredientsEdit);
        this.recipeDirectionsText = (EditText) findViewById(R.id.directionsEdit);

        // Collecting and setting the button
        Button button = (Button) findViewById(R.id.add_recipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //this happens when the button is clicked
                addRecipeToList(v);
            }
        });
    }

    //todo call this internally
    /** Called when the user touches the button */
    public void addRecipeToList(View view) {

        Intent intent = new Intent(getApplicationContext(), RecipeList.class);
        //Sending the data to RecipeList
        intent.putExtra("recipeName", recipeNameText.getText()+"");
        intent.putExtra("recipeIngredients", recipeIngredientsText.getText()+"");
        intent.putExtra("recipeDirections", recipeDirectionsText.getText()+"");
        setResult(2000, intent);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_shoppingList:
                intent = new Intent(EditRecipeActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(EditRecipeActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(EditRecipeActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(EditRecipeActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;
            case R.id.action_help:
                intent = new Intent(EditRecipeActivity.this, HelpActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
