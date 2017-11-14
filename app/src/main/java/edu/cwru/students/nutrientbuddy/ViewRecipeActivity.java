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
    private TextView recipeIngrText;
    private TextView recipeDirText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId4);

        recipeNameText = (TextView) findViewById(R.id.recipe_name_received);
        recipeIngrText = (TextView) findViewById(R.id.recipe_ingr_received);
        recipeDirText = (TextView) findViewById(R.id.recipe_dir_received);

        String s = getIntent().getStringExtra("recipeName");
        Log.v(TAG, "Inside ViewRecipeActivity. Recipe name = " + s);

        recipeNameText.setText(s);

        s = getIntent().getStringExtra("recipeIngredients");
        Log.v(TAG, "Inside ViewRecipeActivity. Recipe ingredients = " + s);

        recipeIngrText.setText(s);

        s = getIntent().getStringExtra("recipeDirections");
        Log.v(TAG, "Inside ViewRecipeActivity. Recipe directions = " + s);

        recipeDirText.setText(s);

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
        switch(item.getItemId()) {
            case R.id.action_home :
                Intent intent = new Intent(ViewRecipeActivity.this, SearchScreenActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe :
                Intent intent2 = new Intent(ViewRecipeActivity.this, RecipeListActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_shoppingList :
                Intent intent3 = new Intent(ViewRecipeActivity.this, ShoppingListActivity.class);
                startActivity(intent3);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }

}
