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
    private Recipe recipe;
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
        recipeNameText = (TextView) findViewById(R.id.recipeItem_name);
        recipeIngredientsText = (TextView) findViewById(R.id.ingredients);
        recipeDirectionsText = (TextView) findViewById(R.id.directions);

        // Collecting the Data
        recipe = (Recipe)getIntent().getSerializableExtra("recipeObject");
        String recipeName = getIntent().getStringExtra("recipeName");
        String recipeIngredients = getIntent().getStringExtra("recipeIngredients");
        String recipeDirections = getIntent().getStringExtra("recipeDirections");

        // Setting the UI text
        recipeNameText.setText(recipeName);
        recipeIngredientsText.setText(recipeIngredients);
        recipeDirectionsText.setText(recipeDirections);

        //
        Button doneButton = (Button)findViewById(R.id.viewrecipedone);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRecipeActivity.this, RecipeListActivity.class);
                startActivity(intent);
            }
        });

        Button deleteButton = (Button) findViewById(R.id.delete_recipe);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Log.v(TAG, "About to send over the ID: " + recipe.getId());
                intent.putExtra("recipeID", recipe.getId()+"");
                setResult(RESULT_OK, intent);

                finish();

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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_shoppingList:
                intent = new Intent(ViewRecipeActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(ViewRecipeActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(ViewRecipeActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(ViewRecipeActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
