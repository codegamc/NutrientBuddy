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
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId2);

        list = new ShoppingList();  // Currently will probably override whatever previous list we made.
        Food apple = new Food("Apple", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
        Food banana = new Food("Banana", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
        Food pear = new Food("Pear", "cal", "fat", "sodium", "carbs", "sugar", "sodium");
        list.addItem(apple);
        list.addItem(banana);
        list.addItem(pear);

        ArrayList<String> shopListNames = list.getItemNames();

        ListView listView = new ListView(getApplicationContext());
        listView = (ListView)findViewById(R.id.list_results);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, shopListNames);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "New Activity with item details", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
