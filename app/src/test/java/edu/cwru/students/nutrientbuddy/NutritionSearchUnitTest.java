package edu.cwru.students.nutrientbuddy;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NutritionSearchUnitTest {

    @Test
    public void searchValidFoodString() throws Exception {
        Nutritionix n = new Nutritionix();

        ArrayList<Food> a = n.searchFood("apple");

        int count = 0;
        //test that no repeats appear, food should equal food2 once, triggering count++
        // therfore count should equal the length of the array
        for (Food food: a ) {
            for (Food food2: a) {
                if(food == food2){
                    count++;
                }
            }
        }

        assertEquals(count, a.size());
    }

    public void returnFoodListStringTest() {
        Nutritionix n = new Nutritionix();
        n.searchFood("apple");
        ArrayList<String> a = n.returnFoodListString();
        // how to test this?
    }
}