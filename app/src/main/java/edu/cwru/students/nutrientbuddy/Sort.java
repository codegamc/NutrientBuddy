package edu.cwru.students.nutrientbuddy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Sort implements SearchMetric {

    @Override
    public ArrayList<Food> noSort(ArrayList<Food> list) {
        return list;
    }

    @Override
    public ArrayList<Food> nameSort(ArrayList<Food> list, final boolean ascending) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
                float first = Float.parseFloat(food1.getName()+"");
                float second = Float.parseFloat(food2.getName()+"");
                if(ascending)
                    return (first<second ?-1:(first==second? 0:1));
                else
                    return (first>second ?-1:(first==second? 0:1));
            }
        };
        Collections.sort(list, comp);
        return list;
    }

    @Override
    public ArrayList<Food> Caloriessort(ArrayList<Food> list, final boolean increasing) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
                float first = Float.parseFloat(food1.getCalories()+"");
                float second = Float.parseFloat(food2.getCalories()+"");
                if(increasing)
                    return (first<second ?-1:(first==second? 0:1));
                else
                    return (first>second ?-1:(first==second? 0:1));
            }
        };
        Collections.sort(list, comp);
        return list;
    }

    @Override
    public ArrayList<Food> Carbssort(ArrayList<Food> list, final boolean increasing) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
                float first = Float.parseFloat(food1.getCarbs()+"");
                float second = Float.parseFloat(food2.getCarbs()+"");
                if(increasing)
                    return (first<second ?-1:(first==second? 0:1));
                else
                    return (first>second ?-1:(first==second? 0:1));
            }
        };
        Collections.sort(list, comp);
        return list;
    }


    @Override
    public ArrayList<Food> TotalFatsort(ArrayList<Food> list, final boolean increasing) {
        Comparator<Food> comp = new Comparator<Food>() {
            @Override
            public int compare(Food food1, Food food2) {
                float first = Float.parseFloat(food1.getTotalFat()+"");
                float second = Float.parseFloat(food2.getTotalFat()+"");
                if(increasing)
                    return (first<second ?-1:(first==second? 0:1));
                else
                    return (first>second ?-1:(first==second? 0:1));
            }
        };
        Collections.sort(list, comp);
        return list;
    }
}
