package edu.cwru.students.nutrientbuddy;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecipeListTest {

    @Test
    public void testAddItem(){
        RecipeList recipeList = new RecipeList();

        Recipe recipe = new Recipe("name","ingredients","directions");
        recipeList.addItem(recipe);
        assertTrue(recipeList.removeRecipe(recipe));

    }
}
