package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RecipeBuilderTest {

    @Rule
    public ActivityTestRule<SearchScreenActivity> mActivityRule = new ActivityTestRule<>(
            SearchScreenActivity.class);

    @Test
    public void verifyCreateNewRecipe() throws Exception {
        //// TODO: 11/16/17
        // navigate to the selection bar

        // press the "new recipe" button

        // fill in strings

        // Click add recipe
    }

    @Test
    public void verifyNavigateToRecipe() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifyNavigateBackToRecipeView() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifyUserCanDeleteRecipe() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifyCreateNewRecipeValidation() throws Exception {
        //// TODO: 11/16/17
    }
}
