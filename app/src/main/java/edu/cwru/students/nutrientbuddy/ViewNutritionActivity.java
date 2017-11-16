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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_nutrition);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        text = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            //text.setText("changit");
            //append(bundle.getString("searchResult"));
            //setTitle(bundle.getString("searchResult"));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        String s = getIntent().getStringExtra("foodName");
        Log.v(TAG, "Inside ViewNutrition. Food name = " + s);

        s = getIntent().getStringExtra("foodCalories");
        Log.v(TAG, "Inside ViewNutrition. Food calories = " + s);

        s = getIntent().getStringExtra("foodTotalFat");
        Log.v(TAG, "Inside ViewNutrition. Food total fat = " + s);

        s = getIntent().getStringExtra("foodTotalCarbs");
        Log.v(TAG, "Inside ViewNutrition. Food total carbs = " + s);

        s = getIntent().getStringExtra("foodProtein");
        Log.v(TAG, "Inside ViewNutrition. Food protein = " + s);

        s = getIntent().getStringExtra("foodSugar");
        Log.v(TAG, "Inside ViewNutrition. Food sugar = " + s);

        s = getIntent().getStringExtra("foodSodium");
        Log.v(TAG, "Inside ViewNutrition. Food sodium = " + s);

    }

}
