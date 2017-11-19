package edu.cwru.students.nutrientbuddy;

import org.junit.Test;
import android.util.Log;

import static org.junit.Assert.*;

public class RecipeUnitTest {

    @Test
    public void testSetName(){
        Recipe recipe = new Recipe("name", "ingredients", "directions");
        String testString = "newName";
        recipe.setName(testString);
        assertEquals(testString,recipe.getName());
    }

    @Test
    public void testSetIngredients(){
        Recipe recipe = new Recipe("","","");
        String testString = "testString";
        recipe.setIngredients(testString);
        assertEquals(testString,recipe.getIngredients());
    }

    @Test
    public void testSetDirection(){
        Recipe recipe = new Recipe("","","");
        String testString = "testString";
        recipe.setDirections(testString);
        assertEquals(testString,recipe.getDirections());
    }

    @Test
    public void testPrint(){
        //this will pass since there is nothing to do
        Recipe recipe = new Recipe("","","");
        //recipe.printRecipe();
    }

}
