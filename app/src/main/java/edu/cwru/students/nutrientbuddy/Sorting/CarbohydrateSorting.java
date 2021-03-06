package edu.cwru.students.nutrientbuddy.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.cwru.students.nutrientbuddy.Food;
import edu.cwru.students.nutrientbuddy.SearchMetric;

/**
 * Created by michael on 11/29/17.
 */

public class CarbohydrateSorting implements SearchMetric {
    @Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
                float first = Float.parseFloat(food1.getCarbs()+"");
                float second = Float.parseFloat(food2.getCarbs()+"");
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
