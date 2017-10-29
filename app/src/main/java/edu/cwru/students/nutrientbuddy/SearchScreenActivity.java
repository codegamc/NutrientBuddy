package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.v7.widget.Toolbar;
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

    private static final String TAG = "MyActivity";
    private Nutritionix n;
    private ArrayList<Food> foods;
    private SearchAdapter adapter;
    private ArrayList<Food> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.n = new Nutritionix();
        this.foods = new ArrayList<Food>();
        this.items = new ArrayList<Food>();

        final SearchView searchView = (SearchView) findViewById(R.id.search_text_area);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v(TAG, query);
                queryNutritionix(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        final ListView listview = (ListView) findViewById(R.id.search_results);


        this.adapter = new SearchAdapter(getApplicationContext(), android.R.layout.list_content, (List)items);
        
        //todo move the GUI to global values

        //todo restructure the oncreate for clarity
/*
       // final ArrayList<Food> items = new ArrayList<Food>();

        //final ListView listview = (ListView) findViewById(R.id.search_results);


        //Nutritionix n = new Nutritionix();
      // this.foods = this.n.searchFood("apple");
      //  ArrayList<String> foodString = n.returnFoodListString();

       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List)foodString);

      // final SearchAdapter adapter = new SearchAdapter(getApplicationContext(), android.R.layout.list_content, (List) foods); */

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //this needs to call the Nutrition Activity
                startNutritionView(items.get(position));
            }
        });

    }

    protected void startNutritionView(Food food){
        Intent intent = new Intent(this, ViewNutritionActivity.class);
        startActivity(intent);
    }

    protected void queryNutritionix(String queryText){
        this.foods = this.n.searchFood(queryText);

        for(Food f: this.foods){
            Log.v(TAG, f.getName());
            //this.adapter.add(f);
            this.items.add(f);
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
