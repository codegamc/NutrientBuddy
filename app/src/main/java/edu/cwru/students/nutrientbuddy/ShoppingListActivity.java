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
<<<<<<< HEAD
    private static final String TAG = "MyActivity";
    private ArrayList<String> shopListNames;

    ListView listView;
=======
    private static final String TAG = "ShoppingListActivity";
>>>>>>> 73dba9f053dbcb50c38400a8458b89f77ebd4948

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId2);

        this.list = new ShoppingList();  // Currently will probably override whatever previous list we made.
        Food apple = new Food("Apple", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
        Food banana = new Food("Banana", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
        Food pear = new Food("Pear", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
        this.list.addItem(apple);
        this.list.addItem(banana);
        this.list.addItem(pear);

<<<<<<< HEAD
        shopListNames = list.getItemNames();
=======
        ArrayList<String> shopListNames = this.list.getItemNames();
>>>>>>> 73dba9f053dbcb50c38400a8458b89f77ebd4948

        listView = new ListView(getApplicationContext());
        listView = (ListView)findViewById(R.id.list_results);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, shopListNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShoppingListActivity.this, ViewShopListItemActivity.class);

                Log.v(TAG, "Selected position " + position);
                Log.v(TAG, "Selected food: " + shopListNames.get(position));
                Log.v(TAG, "Selected food from items: " + list.getShopItems().get(position).getName());


                intent.putExtra("foodName", list.getShopItems().get(position).getName());
                intent.putExtra("foodCalories", list.getShopItems().get(position).getCalories());
                intent.putExtra("foodCarbs", list.getShopItems().get(position).getCarbs());

                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabs);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextScreen = new Intent(getApplicationContext(), EditShoppingListActivity.class);
                startActivityForResult(nextScreen,2000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        String foodName = data.getStringExtra("foodName");
        String foodCalories = data.getStringExtra("foodCalories");
        String foodCarbs = data.getStringExtra("foodCarbs");

        Log.v(TAG, "Food name found: " + foodName);
        Log.v(TAG, "Food calories found: " + foodCalories);
        Log.v(TAG, "Food carbs found: " + foodCarbs);

        list.addItem(new Food(foodName, foodCalories, "fat", "sodium", foodCarbs, "sugar", "sodium"));

        shopListNames = list.getItemNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) shopListNames);

        listView.setAdapter(arrayAdapter);
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
                Intent intent = new Intent(ShoppingListActivity.this, SearchScreenActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe :
                Intent intent2 = new Intent(ShoppingListActivity.this, RecipeListActivity.class);
                startActivity(intent2);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
