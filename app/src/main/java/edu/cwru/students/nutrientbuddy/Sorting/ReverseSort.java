package edu.cwru.students.nutrientbuddy.Sorting;

import java.util.ArrayList;

import edu.cwru.students.nutrientbuddy.Food;
import edu.cwru.students.nutrientbuddy.SearchMetric;

/**
 * Created by michael on 11/29/17.
 */

public class ReverseSort implements SearchMetric {
    @Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        ArrayList<Food> reverseList = new ArrayList<Food>();

        for(int i = list.size() - 1; i > 0; i--){
            reverseList.add(list.get(i));
        }

        return reverseList;
    }
}
