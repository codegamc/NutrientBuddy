package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditShoppingListActivity extends AppCompatActivity {

    private EditText foodNameText;
    private EditText foodQuantityText;
    private EditText foodCostText;

    private static final String TAG = "EditShoppingListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shoplist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ////////////////////// UI Stuff
        // Collecting Views
        foodNameText = (EditText) findViewById(R.id.itemNameEdit);
        foodQuantityText = (EditText) findViewById(R.id.quantityEdit);
        foodCostText = (EditText) findViewById(R.id.expectedPriceEdit);

        // Return
        Button button = (Button) findViewById(R.id.add_fooditem);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToList(v);
            }
        });

    }

    /** Called when the user touches the button */
    public void addItemToList(View view) {

        //todo make this add to a global state to save

        Intent previousScreen = new Intent(getApplicationContext(), ShoppingList.class);
        Log.v(TAG, "Inside Edit. About to add new item: " + foodNameText.getText());
        previousScreen.putExtra("foodName", foodNameText.getText()+"");
        previousScreen.putExtra("foodQuantity", foodQuantityText.getText()+"");
        previousScreen.putExtra("foodCost", foodCostText.getText()+"");
        setResult(2000, previousScreen);

        finish();
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
                intent = new Intent(EditShoppingListActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(EditShoppingListActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(EditShoppingListActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(EditShoppingListActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;
            case R.id.action_help:
                intent = new Intent(EditShoppingListActivity.this, HelpActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
