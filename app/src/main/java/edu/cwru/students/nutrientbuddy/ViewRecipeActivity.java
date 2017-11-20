package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class ViewRecipeActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private TextView recipeNameText;
    private TextView recipeIngredientsText;
    private TextView recipeDirectionsText;

    //Fields for Global User Interface
    private OpenItemsMenuHandler openItemsMenuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //////////////// Initialization stuff
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId4);

        //////////////// Menu Bar Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);

        //////////////// User interface stuff
        // Collecting the UI
        recipeNameText = (TextView) findViewById(R.id.recipe_name_received);
        recipeIngredientsText = (TextView) findViewById(R.id.recipe_ingr_received);
        recipeDirectionsText = (TextView) findViewById(R.id.recipe_dir_received);

        // Collecting the Data
        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeIngredients = getIntent().getStringExtra("recipeIngredients");
        String recipeDirections = getIntent().getStringExtra("recipeDirections");

        // Setting the UI text
        recipeNameText.setText(recipeName);
        recipeIngredientsText.setText(recipeIngredients);
        recipeDirectionsText.setText(recipeDirections);

        //
        Button doneButton = (Button)findViewById(R.id.create_recipe);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRecipeActivity.this, RecipeListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(this.openItemsMenuHandler.onOptionsItemSelected(item.getItemId())){
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

}
