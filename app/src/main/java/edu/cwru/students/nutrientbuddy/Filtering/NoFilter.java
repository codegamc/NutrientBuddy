package edu.cwru.students.nutrientbuddy.Filtering;

import java.util.ArrayList;

import edu.cwru.students.nutrientbuddy.FilterMetric;
import edu.cwru.students.nutrientbuddy.Food;

public class NoFilter implements FilterMetric {

    @Override
    public ArrayList<Food> filter(ArrayList<Food> list) {
        return list;
    }
}
