package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.app.Activity;
import android.view.View;

public class EditRecipeActivity extends AppCompatActivity {

    private static final String TAG = "EditRecipeActivity";

    private EditText recipeNameText;
    private EditText recipeIngrText;
    private EditText recipeDirText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.recipeNameText = (EditText) findViewById(R.id.recipe_name_text);
        this.recipeIngrText = (EditText) findViewById(R.id.recipe_ingredients_text);
        this.recipeDirText = (EditText) findViewById(R.id.recipe_directions_text);

        Button button = (Button) findViewById(R.id.create_recipe);

    }

    /** Called when the user touches the button */
    public void addRecipeToList(View view) {

        Intent previousScreen = new Intent(getApplicationContext(), RecipeList.class);
        //Sending the data to RecipeList
        Log.v(TAG, "About to pass text: " + recipeNameText.getText());
        previousScreen.putExtra("recipeName", recipeNameText.getText()+"");
        previousScreen.putExtra("recipeIngredients", recipeIngrText.getText()+"");
        previousScreen.putExtra("recipeDirections", recipeDirText.getText()+"");
        setResult(2000, previousScreen);

        finish();
    }

}
