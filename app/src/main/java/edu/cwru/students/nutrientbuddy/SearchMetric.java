package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by michael on 11/19/17.
 */

public interface SearchMetric {

    ArrayList<Food> sort(ArrayList<Food> list);

}