package edu.cwru.students.nutrientbuddy;

import android.content.Intent;
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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListActivity extends AppCompatActivity {

    private ShoppingList list;

    //Fields for displaying the Shopping List
    ListView shoppingListView;

    private static final String TAG = "ShoppingListActivity";

    //Fields for Global User Interface
    private OpenItemsMenuHandler openItemsMenuHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        //////////////// Tool Bar Stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId2);

        //////////////// Menu Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);


        this.list = new ShoppingList();

        //////// Testing add item
        ShoppingListItem s = new ShoppingListItem("1", "2", "3");
        this.list.addItem(s);


        //////////////// UI Stuff
        this.shoppingListView = new ListView(getApplicationContext());
        this.shoppingListView = (ListView)findViewById(R.id.list_results);

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

                startActivity(intent);
            }
        });


        //todo rename this!!
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabs);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(getApplicationContext(), EditShoppingListActivity.class);
                startActivityForResult(nextScreen,2000);
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
        setShoppingListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //todo move this to debug only
        String foodName = data.getStringExtra("foodName");
        String foodQuantity = data.getStringExtra("foodQuantity");
        String foodCost = data.getStringExtra("foodCost");

        list.addItem(new ShoppingListItem(foodName, foodQuantity, foodCost));

       //list.addItem(new Food(foodName, foodCalories, "fat", "sodium", foodCarbs, "sugar", "sodium"));

        setShoppingListView();
    }

    private void setShoppingListView(){
        // Setting the list view to be the shopping list
        ArrayList<String> shoppingListNames = this.list.getItemNames();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, shoppingListNames);
        this.shoppingListView.setAdapter(arrayAdapter);
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
