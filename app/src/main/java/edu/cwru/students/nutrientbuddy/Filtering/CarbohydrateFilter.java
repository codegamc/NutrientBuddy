package edu.cwru.students.nutrientbuddy.Filtering;

import java.util.ArrayList;
import java.util.Iterator;

import edu.cwru.students.nutrientbuddy.FilterMetric;
import edu.cwru.students.nutrientbuddy.Food;

public class CarbohydrateFilter implements FilterMetric {

    @Override
    public ArrayList<Food> filter(ArrayList<Food> list) {
        for(Iterator<Food> iterator = list.iterator(); iterator.hasNext();){
            float carbohydrate = Float.parseFloat(iterator.next().get(Food.totalCarbs)+"");
            if(carbohydrate > 18.0)
                iterator.remove();
        }

        return list;
    }
}
