package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewShopListItemActivity extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private TextView foodNameText;
    private TextView foodCalText;
    private TextView foodCarbText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId4);

        foodNameText = (TextView) findViewById(R.id.food_name_received);
        foodCalText = (TextView) findViewById(R.id.food_calories_received);
        foodCarbText = (TextView) findViewById(R.id.food_carbs_received);

        String s = getIntent().getStringExtra("foodName");
        Log.v(TAG, "Inside ViewShopListItemActivity. Food name = " + s);

        foodNameText.setText(s);

        s = getIntent().getStringExtra("foodCalories");
        Log.v(TAG, "Inside ViewShopListItemActivity. Food calories = " + s);

        foodCalText.setText(s);

        s = getIntent().getStringExtra("foodCarbs");
        Log.v(TAG, "Inside ViewShopListItemActivity. Food carbs = " + s);

        foodCarbText.setText(s);

        Button doneButton = (Button)findViewById(R.id.add_fooditem);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewShopListItemActivity.this, ShoppingListActivity.class);
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
                Intent intent = new Intent(ViewShopListItemActivity.this, SearchScreenActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe :
                Intent intent2 = new Intent(ViewShopListItemActivity.this, RecipeListActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_shoppingList :
                Intent intent3 = new Intent(ViewShopListItemActivity.this, ShoppingListActivity.class);
                startActivity(intent3);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
