package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
            //todo
        }

        TextView itemName = (TextView) findViewById(R.id.food_name);
        TextView numCalories = (TextView) findViewById(R.id.numCalories);
        TextView numFat = (TextView) findViewById(R.id.numFat);
        TextView numCarbs = (TextView) findViewById(R.id.numCarbs);
        TextView numProtein = (TextView) findViewById(R.id.numProtein);
        TextView numSugar = (TextView) findViewById(R.id.numSugar);
        TextView numSodium = (TextView) findViewById(R.id.numSodium);

        // Collecting the data
        String foodName = getIntent().getStringExtra("foodName");
        String foodCalories = getIntent().getStringExtra("foodCalories");
        String foodTotalFat = getIntent().getStringExtra("foodTotalFat");
        String foodTotalCarbs = getIntent().getStringExtra("foodTotalCarbs");
        String foodProtein = getIntent().getStringExtra("foodProtein");
        String foodSugar = getIntent().getStringExtra("foodSugar");
        String foodSodium = getIntent().getStringExtra("foodSodium");

        if(foodCalories == null) {
            foodCalories = "0";
        }
        if(foodTotalFat == null){
            foodTotalFat = "0";
        }
        if(foodTotalCarbs == null){
            foodTotalCarbs = "0";
        }
        if(foodProtein == null){
            foodProtein = "0";
        }
        if(foodSugar == null){
            foodSugar = "0";
        }
        if(foodSodium == null){
            foodSodium = "0";
        }

        itemName.setText(foodName);
        numCalories.setText(foodCalories + " kcal");
        numFat.setText(foodTotalFat + " g");
        numCarbs.setText(foodTotalCarbs + " g");
        numProtein.setText(foodProtein + " g");
        numSugar.setText(foodSugar + " g");
        numSodium.setText(foodSodium + " mg");

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
                intent = new Intent(ViewNutritionActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(ViewNutritionActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(ViewNutritionActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(ViewNutritionActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;
            case R.id.action_help:
                intent = new Intent(ViewNutritionActivity.this, HelpActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
