package edu.cwru.students.nutrientbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;

public class EditRecipeActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText text2 = (EditText) findViewById(R.id.recipe_name_text);
        EditText text3 = (EditText) findViewById(R.id.recipe_ingredients_text);
        EditText text4 = (EditText) findViewById(R.id.recipe_directions_text);

    }

}
