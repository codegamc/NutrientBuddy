package edu.cwru.students.nutrientbuddy.Sorting;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.cwru.students.nutrientbuddy.Food;
import edu.cwru.students.nutrientbuddy.SearchMetric;

/**
 * Created by michael on 11/29/17.
 */

public class AlphabeticSorting implements SearchMetric {

    @Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
            if(food1 == null){
                Log.v("Alphabetical Sorting", "True");
            }
            float first = Float.parseFloat(food1.get(Food.name)+"");
            Log.v("Alphabetical Sorting", "" + first);
            float second = Float.parseFloat(food2.get(Food.name)+"");
            Log.v("Alphabetical Sorting", "" + second);
            if(first==second){
                return 0;
            }else{
                if(first < second){
                    return -1;
                }else{
                    return 1;
                }
            }
            }
        };

        Collections.sort(list, comp);
        return list;
    }
}
