package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.util.concurrent.ExecutionError;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RecipeBuilderTest {

    @Rule
    public ActivityTestRule<SearchScreenActivity> mActivityRule = new ActivityTestRule<>(
            SearchScreenActivity.class);

    @Test
    public void verifyCreateNewRecipe() throws Exception {
        this.createRecipe("name", "ingredients", "directions");
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
        this.createRecipe("name", "ingredients", "directions");

        // How to delete a recipe?
        throw new Exception();
    }

    @Test
    public void verifyCreateNewRecipeValidation() throws Exception {
        //// TODO: 11/16/17
    }


    public void createRecipe(String name, String ingredients, String directions){
        // Trigger the recipe builder
        onView(withId(R.layout.activity_recipe_list)).perform(click());

        // Fill stuff out
        onView(withId(R.id.recipe_name_text)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.recipe_ingredients_text)).perform(typeText(ingredients), closeSoftKeyboard());
        onView(withId(R.id.recipe_directions_text)).perform(typeText(directions), closeSoftKeyboard());

        // Close
        onView(withId(R.id.create_recipe)).perform(click());
    }
}
