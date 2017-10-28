package edu.cwru.students.nutrientbuddy;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Nutritionix {

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

    private static final String TAG = "MyActivity";
    private String baseURL;
    private String searchFilters;
    private String searchTags;
    private String apiInfo;
    private Food f;
    private ArrayList<Food> searchResults;

    public Nutritionix(){

        baseURL = "https://api.nutritionix.com/v1_1/search/";
        searchFilters = "?results=0%3A20&cal_min=0&cal_max=50000";
        searchTags = "&fields=item_name%2Cnf_calories%2Cnf_total_fat%2Cnf_sodium%2Cnf_total_carbohydrate%2Cnf_sugars%2Cnf_protein%2Cbrand_name%2Citem_id%2Cbrand_id";
        /*
            * API INFORMATION
            * appId = abc59008
            * appKey = 6df2595ea6849f2ee25f34569992fa4c
         */
        apiInfo = "&appId=abc59008&appKey=6df2595ea6849f2ee25f34569992fa4c";
        searchResults = new ArrayList<Food>();
    }

    public ArrayList<Food> searchFood(String foodSearched) {

        InputStream in = null;
        try {

            URL url = new URL(baseURL + foodSearched + searchFilters + searchTags + apiInfo);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            String s = "";
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                s = s.concat(Character.toString(current));
            }

            // Parse JSON
            JSONArray hits = null;
            try {
                JSONObject reader = new JSONObject(s);
                hits = reader.getJSONArray("hits");

                // How many results do we want to display to the user?
                // Basic items (like "apple") can result in over 1000.
                int numResults = 10;



                for (int i = 0; i < numResults; i++) {

                    f = new Food();

                    JSONObject food = hits.getJSONObject(i);
                    JSONObject foodField = food.getJSONObject("fields");

                    f.setName(foodField.getString("item_name"));
                    f.setTotalCalories(foodField.getString("nf_calories"));
                    f.setTotalFat(foodField.getString("nf_total_fat"));
                    f.setSodium(foodField.getString("nf_sodium"));
                    f.setTotalCarbs(foodField.getString("nf_total_carbohydrate"));
                    f.setTotalSugar(foodField.getString("nf_sugars"));
                    f.setProtein(foodField.getString("nf_protein"));

                    searchResults.add(f);

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

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    public ArrayList<String> returnFoodListString(){
        ArrayList<String> stringList = new ArrayList<String>();

        for(Food f : searchResults){
            stringList.add(f.getName());
        }

        return stringList;
    }



}
