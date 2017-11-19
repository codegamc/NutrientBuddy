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

    // Related to List View
    private ListView listview;
    private ArrayList<Food> foods;

    // Global Fields related to Search
    private boolean dynamicUpdate = false;      //Defaults to false  //todo add a way to make it true in oncreate()
    private ArrayList<String> foodString;

    // Global Fields related to Sorting
    private String sortMethod;
    private SearchMetric seachMetric = new NoSort();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating based on saved instance state
        super.onCreate(savedInstanceState);
        //setting the context
        setContentView(R.layout.activity_search_screen);
        //todo rename the layoutId variable
        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.layoutId);
        //todo update the name here too
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /////////// LIST VIEW STUFF ///////////
        //todo check names
        listview = new ListView(getApplicationContext());
        listview = (ListView) findViewById(R.id.search_results);

        this.foods = new ArrayList<Food>();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchScreenActivity.this, ViewNutritionActivity.class);

                // todo make food serializable to simplify this + future changes (eg. image?)
                intent.putExtra("searchResult",listview.getItemAtPosition(position).toString());
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

        /////////// SEARCHING STUFF ///////////
        //todo rename this search area
        final SearchView searchView = (SearchView) findViewById(R.id.search_text_area);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);

                //todo make this reflect actual state?
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(dynamicUpdate){
                    search(query);
                }else{
                    //do nothing at the moment
                }

                //todo make this reflect actual state?
                return true;
            }

        });

        /////////// SORTING STUFF ///////////
        this.setSeachMetric("NoSort");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /////// PRIVATE METHODS

    private void search(String query){
        Nutritionix nutritionix = new Nutritionix(10);
        nutritionix.loadFoodSearch(query);
        foodString = nutritionix.returnFoodListAsString();

        this.foodString = this.sortList(foodString);

        // todo change name from simple_list_view
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) foodString);
        listview.setAdapter(arrayAdapter);
    }

    private ArrayList<String> sortList(ArrayList<String> list){
        // do something in place to the list of it should change
        return this.seachMetric.sort(list);
    }

    public void setSeachMetric(String seachMetric) {
        //todo there should be a better way to implement this
        if(seachMetric.equals("NoSort")){
            this.seachMetric = new NoSort();
        }
    }

    private class NoSort implements SearchMetric {

        @Override
        public ArrayList sort(ArrayList list) {
            return list;
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
