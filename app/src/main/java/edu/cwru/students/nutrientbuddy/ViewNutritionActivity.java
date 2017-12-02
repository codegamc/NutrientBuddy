package edu.cwru.students.nutrientbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

public class ViewNutritionActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    TextView text;
    private TextView foodNameView;
    private TextView foodCaloriesView;
    private TextView foodTotalFatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nutrition);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            //todo
        }

        ///////////////  Stuff for UI
        //
        // what is this
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Collecting the views
        foodNameView = (TextView) findViewById(R.id.food_name_received);
        foodCaloriesView = (TextView) findViewById(R.id.calories_received);
        foodTotalFatView = (TextView) findViewById(R.id.total_fat_received);

        // Collecting the data
        String foodName = getIntent().getStringExtra("foodName");
        String foodCalories = getIntent().getStringExtra("foodCalories");
        String foodTotalFat = getIntent().getStringExtra("foodTotalFat");
        String foodTotalCarbs = getIntent().getStringExtra("foodTotalCarbs");
        String foodProtein = getIntent().getStringExtra("foodProtein");
        String foodSugar = getIntent().getStringExtra("foodSugar");
        String foodSodium = getIntent().getStringExtra("foodSodium");

        foodCaloriesView.setText(foodCalories + "kcal");
        foodTotalFatView.setText(foodTotalFat + "g");
        foodNameView.setText(foodName);
    }

}
