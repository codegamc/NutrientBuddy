package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;

class NoSort implements SearchMetric {

    @Override
    public ArrayList sort(ArrayList list) {
        return list;
    }
}