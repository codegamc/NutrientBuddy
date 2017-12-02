package edu.cwru.students.nutrientbuddy.Sorting;

import java.util.ArrayList;

import edu.cwru.students.nutrientbuddy.Food;
import edu.cwru.students.nutrientbuddy.SearchMetric;

/**
 * Created by michael on 11/29/17.
 */

public class CompositeSort implements SearchMetric {
    private ArrayList<SearchMetric> steps;

    public CompositeSort(){
        this.steps = new ArrayList<SearchMetric>();
    }

    public void addStep(SearchMetric step){
        this.steps.add(step);
    }

    public void removeLastStep(){
        this.steps.remove(this.steps.size() -1);
    }

    public void removeStep(int location){
        if(location < this.steps.size()){
            this.steps.remove(location);
        }

    }

    @Override
    public ArrayList<Food> sort(ArrayList<Food> list) {
        for(SearchMetric step: this.steps){
            list = step.sort(list);
        }

        return list;
    }
}
