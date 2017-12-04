package edu.cwru.students.nutrientbuddy;

import edu.cwru.students.nutrientbuddy.Sorting.*;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchScreenActivity extends AppCompatActivity{

    // Related to List View
    private ListView listview;

    // Global Fields related to Search
    private boolean dynamicUpdate = false;      //Defaults to false  //todo add a way to make it true in oncreate()
    private ArrayList<Food> foods;
    private ArrayList<String> foodString;

    // Global Fields related to Sorting
    private SearchMetric searchMetric;

    //Fields for Global User Interface
    private OpenItemsMenuHandler openItemsMenuHandler;

    // Fields for the spinner
    //...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //creating based on saved instance state
        super.onCreate(savedInstanceState);
        //setting the context
        setContentView(R.layout.activity_search_screen);

        // Tool Bar Stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Menu Bar Stuff
        this.openItemsMenuHandler = new OpenItemsMenuHandler(this);

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
                intent.putExtra("searchResult",     listview.getItemAtPosition(position).toString());
                intent.putExtra("foodName",             foods.get(position).getName());
                intent.putExtra("foodCalories",         foods.get(position).getCalories());
                intent.putExtra("foodTotalFat",         foods.get(position).getTotalFat());
                intent.putExtra("foodTotalCarbs",       foods.get(position).getCarbs());
                intent.putExtra("foodSodium",           foods.get(position).getSodium());
                intent.putExtra("foodSugar",            foods.get(position).getSugar());
                intent.putExtra("foodProtein",          foods.get(position).getProtein());

                startActivity(intent);
            }



        });



        /////////// SEARCHING STUFF ///////////
        //todo rename this search area
        final SearchView searchView = (SearchView) findViewById(R.id.search_text_area);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getBoolean("Text Entered")){
            String searchEntered = bundle.getString("Search Entered");
            searchView.setQuery(searchEntered, true);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                //make this reflect actual state?
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(dynamicUpdate){
                    search(query);
                }else{
                    //do nothing at the moment, since we don't want the thing running
                }

                //make this reflect actual state?
                return true;
            }

        });

        /////////// SORTING STUFF ///////////
        this.searchMetric = (SearchMetric) (new NoSort());

        /////////// Setting up Spinner
        Spinner sortingSpinner = (Spinner) findViewById(R.id.sort_spinner);

        ArrayAdapter<String> sortingAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sort));

        sortingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortingSpinner.setAdapter(sortingAdapter);
        sortingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                switch (pos){
                    case 0: // No Sort
                        searchMetric = new NoSort();
                        break;
                    case 1: // Alphabetical Sorting
                        searchMetric = new AlphabeticSorting();
                        break;
                    case 2: // Reverse Alphabetical Sorting
                        CompositeSort metric = new CompositeSort();

                        metric.addStep(new AlphabeticSorting());
                        metric.addStep(new ReverseSort());

                        searchMetric = metric;
                        break;
                    case 3: // Caloric Sorting
                        searchMetric = new CalorySort();
                        break;
                    case 4: //Carbohydrate Sorting
                        searchMetric = new CarbohydrateSorting();
                        break;
                    case 5: //Fat Content Sorting
                        searchMetric = new FatSorting();
                        break;
                }

                updateDisplay();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //searchMetric = new NoSort();
            }
        });
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
                intent = new Intent(SearchScreenActivity.this, ShoppingListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_barcode:
                intent = new Intent(SearchScreenActivity.this, BarcodeActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_recipe:
                intent = new Intent(SearchScreenActivity.this, RecipeListActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(SearchScreenActivity.this, SearchScreenActivity.class);
                intent.putExtra("Text Entered", false);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /////// PRIVATE METHODS

    private void search(String query){
        // Even though this is only a single executed line, im keeping this hear, since it is way
        // more apparent what "Search()" does compared to the single line, also this enables faster
        // changes should we want to add more data pipe-lining in the search method - such as data
        // validation or something like that (we should do that)
        new FoodSearchAgent().execute(query);
    }

    private void updateDisplay(){
        Log.v("SearchActivity","UpdateDisplay Gunna run");

        if(this.foods != null){

            this.foodString = Nutritionix.convertFoodListToString(sortList(foods));
            // todo change name from simple_list_view
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, (List) foodString);
            this.listview.setAdapter(arrayAdapter);

        }

        Log.v("SearchActivity","UpdateDisplay has ran");

    }

    private ArrayList<Food> sortList(ArrayList<Food> list){
        return this.searchMetric.sort(list);
    }

    private class FoodSearchAgent extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Nutritionix nutritionix = new Nutritionix(10);
            nutritionix.loadFoodSearch(strings[0]);
            foods = nutritionix.returnFoodListAsFood();

            return null;
        }

        protected void onPostExecute(Void v) {
            updateDisplay();
        }
    }

}
