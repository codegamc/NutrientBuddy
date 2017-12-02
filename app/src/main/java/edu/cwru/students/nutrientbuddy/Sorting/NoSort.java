package edu.cwru.students.nutrientbuddy.Sorting;

import java.util.ArrayList;
import edu.cwru.students.nutrientbuddy.Food;
import edu.cwru.students.nutrientbuddy.SearchMetric;

public class NoSort implements SearchMetric {

    //@Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        return list;
    }

}