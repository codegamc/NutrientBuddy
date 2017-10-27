package edu.cwru.students.nutrientbuddy;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class NutritionixUnitTest {

    @Test
    public void nutritionix_ResultsCanBeFetched() throws Exception{
        Nutritionix n = new Nutritionix();
        ArrayList<Food> list = n.searchFood("Apple");
        assertNotEquals(0, (list.size()));
    }

    @Test
    public void nutritionix_EmptyListIfNoResults() throws Exception{
        Nutritionix n = new Nutritionix();
        ArrayList<Food> list = n.searchFood("dkjfhdskjfndsm");
        assertEquals(0, (list.size()));
    }

    @Test
    public void nutritionix_CanGetFoodInfo() throws Exception{
        Nutritionix n = new Nutritionix();
        ArrayList<Food> list = n.searchFood("Apple");
        Food f = list.get(0);
        assertNotEquals("", f.getName());
        assertNotEquals("", f.getCalories());
    }

}
