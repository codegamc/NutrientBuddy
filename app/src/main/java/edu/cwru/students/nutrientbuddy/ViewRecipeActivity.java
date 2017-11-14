package edu.cwru.students.nutrientbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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



    }

}
