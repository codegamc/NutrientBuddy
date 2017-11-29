package edu.cwru.students.nutrientbuddy.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.cwru.students.nutrientbuddy.Food;
import edu.cwru.students.nutrientbuddy.SearchMetric;

/**
 * Created by michael on 11/29/17.
 */

public class FatSorting implements SearchMetric {
    @Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
                float first = Float.parseFloat(food1.getTotalFat()+"");
                float second = Float.parseFloat(food2.getTotalFat()+"");
                return (first<second ?-1:(first==second? 0:1));

            }
        };
        Collections.sort(list, comp);
        return list;
    }
}
