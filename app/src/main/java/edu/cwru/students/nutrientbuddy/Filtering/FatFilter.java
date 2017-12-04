package edu.cwru.students.nutrientbuddy.Filtering;

import java.util.ArrayList;
import java.util.Iterator;

import edu.cwru.students.nutrientbuddy.FilterMetric;
import edu.cwru.students.nutrientbuddy.Food;


public class FatFilter implements FilterMetric {

    @Override
    public ArrayList<Food> filter(ArrayList<Food> list) {
        for(Iterator<Food> iterator = list.iterator(); iterator.hasNext();){
            float totalFat = Float.parseFloat(iterator.next().get(Food.totalFat)+"");
            if(totalFat > 15.0)
                iterator.remove();
        }

        return list;
    }
}
