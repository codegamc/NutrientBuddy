package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditShoppingListActivity extends AppCompatActivity {

    private EditText foodNameText;
    private EditText foodQuantityText;
    private EditText foodCostText;

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
        previousScreen.putExtra("foodName", foodNameText.getText()+"");
        previousScreen.putExtra("foodQuantity", foodQuantityText.getText()+"");
        previousScreen.putExtra("foodCost", foodCostText.getText()+"");
        setResult(2000, previousScreen);

        finish();
    }
}
