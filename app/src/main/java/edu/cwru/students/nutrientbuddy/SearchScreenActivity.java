package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchScreenActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private Nutritionix n;
    private ArrayList<Food> foods;
    private SearchAdapter adapter;
    private ArrayList<Food> items;
    private ListView listview;
    private ArrayList<String> foodString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        n = new Nutritionix();
        foods = new ArrayList<Food>();
        items = new ArrayList<Food>();
        foodString = new ArrayList<String>();
        listview = new ListView(getApplicationContext());

        final SearchView searchView = (SearchView) findViewById(R.id.search_text_area);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v(TAG, query);
                queryNutritionix(query);
                foods = n.searchFood(query);

                listview = (ListView) findViewById(R.id.search_results);

                Log.v(TAG, "About to log for the foods!!");


                foodString = n.returnFoodListString();

                Log.v(TAG, "Got here.");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List)foodString);

                listview.setAdapter(arrayAdapter);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                queryNutritionix(newText);
                //foods = n.searchFood(query);
                listview = (ListView) findViewById(R.id.search_results);
                foodString = n.returnFoodListString();
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List)foodString);
                listview.setAdapter(arrayAdapter);

                return true;
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //this needs to call the Nutrition Activity
                //startNutritionView(items.get(position));
                Intent intent = new Intent(SearchScreenActivity.this, ViewNutritionActivity.class);

                Log.v(TAG, "Selected position " + position);
                Log.v(TAG, "Selected food: " + foodString.get(position));
                Log.v(TAG, "Selected food from items: " + foods.get(position).getName());

               //intent.putExtra("foodName", foodString.get(position));

                intent.putExtra("foodName", foods.get(position).getName());
                intent.putExtra("foodCalories", foods.get(position).getCalories());
                intent.putExtra("foodTotalFat", foods.get(position).getTotalFat());
                intent.putExtra("foodTotalCarbs", foods.get(position).getCarbs());
                intent.putExtra("foodSodium", foods.get(position).getSodium());
                intent.putExtra("foodSugar", foods.get(position).getSugar());
                intent.putExtra("foodProtein", foods.get(position).getProtein());


                startActivity(intent);
            }
        });

    }

    protected void startNutritionView(Food food){
        Intent intent = new Intent(this, ViewNutritionActivity.class);
        startActivity(intent);
    }

    protected void queryNutritionix(String queryText){
        foods.clear();
        Log.v(TAG, "Inside queryNutritionix()");
        this.foods = this.n.searchFood(queryText);

        for(Food f: this.foods){
            Log.v(TAG, f.getName());
            //this.adapter.add(f);
            this.items.add(f);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.menu_search_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_shoppingList :
                Intent intent = new Intent(SearchScreenActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_recipe:
                Intent intent3 = new Intent(SearchScreenActivity.this, RecipeListActivity.class);
                startActivity(intent3);
                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }


    private class SearchAdapter extends ArrayAdapter<Food> {

        HashMap<Food, Integer> idMap = new HashMap<Food, Integer>();

        public SearchAdapter(Context context, int textViewResourceId, List<Food> foods){
            super(context, textViewResourceId, foods);
        }

        @Override
        public long getItemId(int position){
            Food item = getItem(position);
            return this.idMap.get(item);
        }

    }
}
