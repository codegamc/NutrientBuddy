package edu.cwru.students.nutrientbuddy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ShoppingListTest {

    @Rule
    public ActivityTestRule<ShoppingListActivity> mActivityRule = new ActivityTestRule<>(
            ShoppingListActivity.class);

    @Test
    public void foodAddedIsDisplayed() throws Exception {
        onView(withId(R.id.search_text_area)).perform(typeText("apple"), closeSoftKeyboard());

        //Add food to list

        // check list, ensure its there
    }

    @Test
    public void userCannotAddEmptyItem() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void foodDeletedGoesAway() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void shoppingListSavesLocally() throws Exception {
        //// TODO: 11/16/17  
    }
}
