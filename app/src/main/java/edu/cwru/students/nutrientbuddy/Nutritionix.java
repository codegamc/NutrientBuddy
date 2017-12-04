package edu.cwru.students.nutrientbuddy;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
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

    private static final String TAG = "NutritionixApiWrapper";
    private String baseURL;
    private String searchFilters;
    private String searchTags;
    private String apiInfo;
    private ArrayList<Food> searchResults;

    // Fields related to Configuration
    private int numberOfResults;
    private String[] nutritionValues = {Food.name, Food.totalCalories,Food.totalFat,Food.sodium,
            Food.totalCarbs,Food.totalCalories,Food.protein};

    // do not keep this - its for testing
    private Food f;

    public Nutritionix(int numberOfResults){

        baseURL = "https://api.nutritionix.com/v1_1/search/";
        searchFilters = "?results=0%3A20&cal_min=0&cal_max=50000";
        searchTags = "&fields=item_name%2Cnf_calories%2Cnf_total_fat%2Cnf_sodium%2Cnf_total_carbohydrate%2Cnf_sugars%2Cnf_protein%2Cbrand_name%2Citem_id%2Cbrand_id";
        /*
            * API INFORMATION
            * appId = abc59008
            * appKey = 6df2595ea6849f2ee25f34569992fa4c
         */
        apiInfo = "&appId=abc59008&appKey=6df2595ea6849f2ee25f34569992fa4c";

        //setting the config info
        this.numberOfResults = numberOfResults;

        this.searchResults = new ArrayList<Food>();
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

                f = new Food();

                for (int i = 0; i < numResults; i++) {

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


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    public boolean loadFoodSearch(String queryText){
        Log.v(TAG, "Starting food search");
        //init values
        URL url;

        // Build the URL to query against
        try{
            url = new URL(baseURL + queryText + searchFilters + searchTags + apiInfo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } finally {
            //
        }

        InputStream in = null;
        String dataString = "";
        //LIMIT TRY CATCH TO CODE THAT CAN FAIL
        try {
            // Make Connection attempt
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char)data;
                data = isw.read();
                dataString = dataString.concat(Character.toString(current));
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            Log.v(TAG, "Finally...");
            Log.v(TAG, dataString);
        }

        // Parse JSON
        ArrayList<Food> foods = buildJSON(dataString);
        this.searchResults = foods;

        //Log.v(TAG, foods.toString());

        return true;
    }

    public ArrayList<Food> returnFoodListAsFood(){
        ArrayList<Food> foodList = new ArrayList<Food>();

        for(Food f : this.searchResults){
            foodList.add(f);
        }

        return foodList;
    }

    public static ArrayList<String> convertFoodListToString(ArrayList<Food> foods){
        ArrayList<String> stringList = new ArrayList<String>();
        try{
            for(Food f : foods){
                stringList.add(f.get(Food.name));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


        return stringList;
    }

    ///////////// PRIVATE METHODS

    //todo : we should ensure that we check the NumberOFResultsToReturn againt the number of results the API has returned to us

    private ArrayList<Food> buildJSON(String data){
        ArrayList<Food> foods = new ArrayList<Food>();
        try {

            // Build a reader to handle parsing
            JSONObject reader = new JSONObject(data);
            // Collect the array of resolts
            JSONArray json = reader.getJSONArray("hits");

            //Build a food object to hold results
            Food food;
            //iterate across the results a specific number of times
            for (int i = 0; i < this.numberOfResults -1; i++) {
                food = new Food();

                //This is the food as a JSON object
                JSONObject foodJSON = json.getJSONObject(i);
                // This is the fields we want
                JSONObject foodField = foodJSON.getJSONObject("fields");

                for(String nutritionValue: this.nutritionValues){
                    food.set(nutritionValue, foodField.getString(nutritionValue));
                }

                /*if(checkForRepeat(foods, food)){
                    i--;
                }else{
                    foods.add(food);
                }*/

                foods.add(food);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } finally {
            for (Food f: foods) {
                Log.v(TAG, f.get(Food.name) + "");
            }
        }

        return foods;

    }

    private boolean checkForRepeat(ArrayList<Food> foods, Food food){
        for(Food f: foods){
            if(f.get(Food.name).equals(food.get(Food.name))){
                return true;
            }
        }

        return false;
    }

}
