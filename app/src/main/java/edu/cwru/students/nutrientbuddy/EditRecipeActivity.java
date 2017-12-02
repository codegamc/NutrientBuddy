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
        this.recipeNameText = (EditText) findViewById(R.id.recipe_name_text);
        this.recipeIngredientsText = (EditText) findViewById(R.id.recipe_ingredients_text);
        this.recipeDirectionsText = (EditText) findViewById(R.id.recipe_directions_text);

        // Collecting and setting the button
        Button button = (Button) findViewById(R.id.create_recipe);
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

        // this is not the best way to handle this

        Intent intent = new Intent(getApplicationContext(), RecipeList.class);
        //Sending the data to RecipeList
        intent.putExtra("recipeName", recipeNameText.getText()+"");
        intent.putExtra("recipeIngredients", recipeIngredientsText.getText()+"");
        intent.putExtra("recipeDirections", recipeDirectionsText.getText()+"");
        setResult(2000, intent);

        finish();
    }

}
