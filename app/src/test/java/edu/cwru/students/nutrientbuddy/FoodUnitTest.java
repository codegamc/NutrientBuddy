package edu.cwru.students.nutrientbuddy;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodUnitTest {

    @Test
    public void setFoodName_isCorrect() throws Exception{
        Food f = new Food();
        f.setName("Apple");
        assertEquals("Apple", f.getName());
    }

    @Test
    public void setFoodName_isNotEmpty() throws Exception{
        Food f = new Food();
        f.setName("Apple");
        assertNotEquals("", f.getName());
    }

    @Test
    public void setFoodName_CaloriesAreEmpty() throws Exception{
        Food f = new Food();
        f.setName("Apple");
        assertEquals("", f.getCalories());
    }

    @Test
    public void setTotalFat_isCorrect() throws Exception{
        Food f = new Food();
        f.setTotalFat("10");
        assertEquals("10", f.getTotalFat());
    }

    @Test
    public void newFood_isCorrect() throws Exception{

        Food f = new Food("Carrot", "10", "20", "30", "40", "50", "60");
        assertEquals("Carrot", f.getName());
        assertEquals("10", f.getCalories());
        assertEquals("20", f.getTotalFat());
        assertEquals("30", f.getSodium());
        assertEquals("40", f.getCarbs());
        assertEquals("50", f.getSugar());
        assertEquals("60", f.getProtein());
    }

    @Test
    public void canChangeFood() throws Exception{

        Food f = new Food("Carrot", "10", "20", "30", "40", "50", "60");

        f.setName("Almond");
        f.setProtein("100");

        assertEquals("Almond", f.getName());
        assertEquals("10", f.getCalories());
        assertEquals("20", f.getTotalFat());
        assertEquals("30", f.getSodium());
        assertEquals("40", f.getCarbs());
        assertEquals("50", f.getSugar());
        assertEquals("100", f.getProtein());
    }
}
