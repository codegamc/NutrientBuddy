package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;

class NoSort implements SearchMetric {

    @Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        return list;
    }
}