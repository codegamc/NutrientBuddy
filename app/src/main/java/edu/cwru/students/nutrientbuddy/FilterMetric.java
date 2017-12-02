package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;

public interface FilterMetric {

    ArrayList<Food> caloriesBound(ArrayList<Food> list);
    ArrayList<Food> carbsBound(ArrayList<Food> list);
    ArrayList<Food> fatBound(ArrayList<Food> list);
    ArrayList<Food> proteinBound(ArrayList<Food> list);
    ArrayList<Food> vegetarian(ArrayList<Food> list);

}