package edu.cwru.students.nutrientbuddy.Filtering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.cwru.students.nutrientbuddy.Food;

public class DuplicationFilter{
    public ArrayList<Food> filter(ArrayList<Food> list) {
        Set<Food> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);

        return list;
    }

}
