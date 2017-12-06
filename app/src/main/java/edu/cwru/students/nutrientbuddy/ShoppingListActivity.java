package edu.cwru.students.nutrientbuddy;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.StringSearch;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private ShoppingList list;

    private ShoppingDatabaseHelper shoppingDatabaseHelper;

    //Fields for displaying the Shopping List
    ListView shoppingListView;

    private static final String TAG = "ShoppingListActivity";

    //Fields for Global User Interface
    private OpenItemsMenuHandler openItemsMenuHandler;

    private ArrayList<String> shoppingListNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shoppingDatabaseHelper = new ShoppingDatabaseHelper(this);

        //////////////// Tool Bar Stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //////////////// Menu Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);



        this.list = new ShoppingList();

        //////// Testing add item
       // ShoppingListItem shop = new ShoppingListItem("1", "2", "3");
        //this.list.addItem(shop);


        // DB stuff
        Cursor c = shoppingDatabaseHelper.query(DatabaseHelper.TABLE_USERS, DatabaseHelper.COL_NAME);


        while(c.moveToNext()){
            String col_name = c.getString(c.getColumnIndex("name"));
            String col_quantity = c.getString(c.getColumnIndex("quantity"));
            String col_cost = c.getString((c.getColumnIndex("cost")));

            ShoppingListItem s = new ShoppingListItem(col_name, col_quantity, col_cost);

            list.addItem(s);
        }


        //////////////// UI Stuff
        this.shoppingListView = new ListView(getApplicationContext());
        this.shoppingListView = (ListView)findViewById(R.id.shop_list_results);

        this.shoppingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoppingListActivity.this, ViewShopListItemActivity.class);

                Log.v(TAG, "Selected position " + position);
                Log.v(TAG, "Selected food: " + list.getShopItems().get(position));
                Log.v(TAG, "Selected food from items: " + list.getShopItems().get(position).getName());


                intent.putExtra("foodName", list.getShopItems().get(position).getName());
                intent.putExtra("foodQuantity", list.getShopItems().get(position).getQuantity());
                intent.putExtra("foodCost", list.getShopItems().get(position).getCost());

                //startActivity(intent);

                startActivityForResult(intent, 4000);
            }
        });


        //////////////// Shopping List Stuff
        //this.list = new ShoppingList();  // Currently will probably override whatever previous list we made.

       /* if(false){ //todo access shared preferences to toggle debug
            Food apple = new Food("Apple", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
            Food banana = new Food("Banana", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
            Food pear = new Food("Pear", "cal", "fat", "sodium", "carbs", "sugar", "sodium");

            this.list.addItem(apple);
            this.list.addItem(banana);
            this.list.addItem(pear);
        }*/

        // Setting the list view to be the shopping list
        this.shoppingListNames = this.list.getItemNames();
        setShoppingListView(shoppingListNames);

        Button sortShopAlpha = (Button) findViewById(R.id.shopButtonAlpha);
        sortShopAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(shoppingListNames);
                setShoppingListView(shoppingListNames);
            }
        });

        Button sortShopRevAlpha = (Button) findViewById(R.id.shopButtonRevAlpha);
        sortShopRevAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(shoppingListNames);
                Collections.reverse(shoppingListNames);
                setShoppingListView(shoppingListNames);
            }
        });

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.shopListFAB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingListActivity.this, EditShoppingListActivity.class);
                startActivityForResult(intent,2000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //todo move this to debug only
        String foodName = data.getStringExtra("foodName");
        String foodQuantity = data.getStringExtra("foodQuantity");
        String foodCost = data.getStringExtra("foodCost");

        addNewListItemDB(foodName, foodQuantity, foodCost);


        list.addItem(new ShoppingListItem(foodName, foodQuantity, foodCost));

       //list.addItem(new Food(foodName, foodCalories, "fat", "sodium", foodCarbs, "sugar", "sodium"));
        Log.v(TAG, "About to add new item: " + foodName);
       // this.list.addItem(new ShoppingListItem(foodName, foodQuantity, foodCost));

        ArrayList<String> shoppingListNames = this.list.getItemNames();
        setShoppingListView(shoppingListNames);
    }

    private void setShoppingListView(ArrayList<String> shopListNames){
        // Setting the list view to be the shopping list
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, shopListNames);
        this.shoppingListView.setAdapter(arrayAdapter);
    }

    private void addNewListItemDB(String name, String quantity, String cost){
        ContentValues values = new ContentValues();
        if(name != null) {
            values.put(ShoppingDatabaseHelper.COL_NAME, name);
        }
        if(quantity != null){
            values.put(ShoppingDatabaseHelper.COL_QUANTITY, quantity);
        }

        if(cost != null){
            values.put(ShoppingDatabaseHelper.COL_COST, cost);
        }

        shoppingDatabaseHelper.insert(DatabaseHelper.TABLE_USERS, values);

    }

    private void removeRecipeDB(long listItem_ID){
        shoppingDatabaseHelper.delete(DatabaseHelper.TABLE_USERS, listItem_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_shoppingList:
                intent = new Intent(ShoppingListActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(ShoppingListActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(ShoppingListActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(ShoppingListActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
