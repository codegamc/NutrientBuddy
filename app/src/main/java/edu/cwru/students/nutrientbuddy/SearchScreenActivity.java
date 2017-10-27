package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listview = (ListView) findViewById(R.id.search_results);
        final ArrayList<Food> items = new ArrayList<Food>();
        final SearchAdapter adapter = new SearchAdapter(this, android.R.layout.list_content, items);

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
