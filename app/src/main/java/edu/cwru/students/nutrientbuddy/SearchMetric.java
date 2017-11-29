package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by michael on 11/19/17.
 */

public interface SearchMetric {

    ArrayList<Food> noSort(ArrayList<Food> list);
    ArrayList<Food> nameSort(ArrayList<Food> list, boolean ascending);
    ArrayList<Food> Caloriessort(ArrayList<Food> list, boolean increasing);
    ArrayList<Food> TotalFatsort(ArrayList<Food> list, boolean increasing);
    ArrayList<Food> Carbssort(ArrayList<Food> list, boolean increasing);

}