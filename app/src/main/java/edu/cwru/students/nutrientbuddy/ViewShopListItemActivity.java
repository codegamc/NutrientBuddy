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

    // Fields for UI stuff
    private TextView foodNameText;
    private TextView foodQuantityText;
    private TextView foodCostText;

    //Fields for Global User Interface
    private OpenItemsMenuHandler openItemsMenuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_shop_list_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.shoplistitemlayout);

        //////////////// Menu Bar Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);

        //////////////// UI stuff
        // Collect references to UI objects
        foodNameText = (TextView) findViewById(R.id.shopItem_name);
        foodQuantityText = (TextView) findViewById(R.id.numQuantity);
        foodCostText = (TextView) findViewById(R.id.numPrice);
        // Collect data
        String foodName = getIntent().getStringExtra("foodName");
        String foodQuantity = getIntent().getStringExtra("foodQuantity");
        String foodCost = getIntent().getStringExtra("foodCost");
        // push to UI
        foodNameText.setText(foodName);
        foodQuantityText.setText(foodQuantity);
        foodCostText.setText(foodCost);
        // Button stuff
        Button doneButton = (Button)findViewById(R.id.shoplistitemdone);
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
        if(this.openItemsMenuHandler.onOptionsItemSelected(item.getItemId())){
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}
