package edu.cwru.students.nutrientbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.os.StrictMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);



        InputStream in = null;
        try {



            String baseURL = "https://api.nutritionix.com/v1_1/search/";

            /*
            *   FOOD SEARCHED
            *   This will be the value entered by the user
            * */
            String foodSearched = "taco";


            String searchFilters = "?results=0%3A20&cal_min=0&cal_max=50000";

            /*
                SEARCH RESULTS: these are some of the API fields that can be requested

                nf_calories (kcal)
                nf_calories_from_fat (kcal)
                nf_total_fat (g)
                nf_saturated_fat (g)
                nf_monounsaturated_fat (g)
                nf_polyunsaturated_fat (g)
                nf_trans_fatty_acid (g)
                nf_cholesterol (mg)
                nf_sodium (mg)
                nf_total_carbohydrate (g)
                nf_dietary_fiber (g)
                nf_sugars (g)
                nf_protein (g)
                nf_vitamin_a_dv (DV% expressed as integer: e.g. 20 -> 20% daily value of vitamin A)
                nf_vitamin_c_dv
                nf_calcium_dv
                nf_iron_dv
                nf_potassium (mg)
                nf_servings_per_container
                nf_serving_size_quantity (if serving size is 1 cup, this will return 1)


                The specific fields we want to fetch must be appended to the URL.
            */
            String searchResults = "&fields=item_name%2Cnf_calories%2Cnf_total_fat%2Cnf_sodium%2Cnf_total_carbohydrate%2Cnf_sugars%2Cnf_protein%2Cbrand_name%2Citem_id%2Cbrand_id";

            /*
            *
            * API INFORMATION
            * appId = abc59008
            * appKey = 6df2595ea6849f2ee25f34569992fa4c
            *
            * */
            String apiInfo = "&appId=abc59008&appKey=6df2595ea6849f2ee25f34569992fa4c";

            URL url = new URL(baseURL + foodSearched + searchFilters + searchResults + apiInfo);

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            String s = "";
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                s = s.concat(Character.toString(current));
            }

            // Print JSON
            Log.v(TAG, s);


            // Parse JSON and print individual food items + calories
            JSONArray hits = null;
            try {
                JSONObject reader = new JSONObject(s);
                hits = reader.getJSONArray("hits");

                // How many results do we want to display?
                int numResults = 10;

                for (int i=0; i < numResults; i++) {

                    JSONObject food = hits.getJSONObject(i);
                    JSONObject foodField = food.getJSONObject("fields");
                    String foodName = "Food: " + foodField.getString("item_name");
                    Log.v(TAG, foodName);
                    String calories = "Calories: " + foodField.getString("nf_calories");
                    Log.v(TAG,calories);
                    String tot_fat = "Total Fat: " + foodField.getString("nf_total_fat") + " g";
                    Log.v(TAG,tot_fat);
                    String sodium = "Sodium: " + foodField.getString("nf_sodium") + " mg";
                    Log.v(TAG,sodium);
                    String carbs = "Carbohydrates : " + foodField.getString("nf_total_carbohydrate") + " g";
                    Log.v(TAG,carbs);
                    String tot_sugars = "Total Sugars: " + foodField.getString("nf_sugars") + " g";
                    Log.v(TAG,tot_sugars);
                    String protein = "Protein: " + foodField.getString("nf_protein") + " g";
                    Log.v(TAG,protein);


                    // Using Food object:
                    Food f = new Food(foodName, calories, tot_fat, sodium, carbs, tot_sugars, protein);

                    Log.v(TAG, "****************");
                    Log.v(TAG, "Created new food object!");
                    Log.v(TAG, f.getName());
                    Log.v(TAG, f.getCalories());
                    Log.v(TAG, f.getTotalFat());
                    Log.v(TAG, f.getSugar());
                    Log.v(TAG, f.getSodium());
                    Log.v(TAG, f.getCarbs());
                    Log.v(TAG, f.getProtein());
                    Log.v(TAG, "****************");

                    foodName = "";
                    calories = "";
                    tot_fat = "";
                    sodium = "";
                    carbs = "";
                    tot_sugars = "";
                    protein = "";
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
