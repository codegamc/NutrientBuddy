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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DisplayNutritionInformationTest {

    @Rule
    public ActivityTestRule<SearchScreenActivity> mActivityRule = new ActivityTestRule<>(
            SearchScreenActivity.class);

    @Test
    public void verifySelectionOfSearchResults() throws Exception {
        // Make search
        //onView(withId(R.id.search_text_area)).perform(typeText("apple"), closeSoftKeyboard());
        // Click the selection (somehow?)

        // Verify contents // this should be in a different Activity?
        // verify activity?

        // Hit back button and return

        // verify existing list
        //onView(withId(R.id.list_results)).perform();

        //onView(withId(R.id.list_results)).perform(click());

        //// TODO: 11/16/17

    }



    @Test
    public void verifyBackFunctionality() throws Exception {
        // Make search
        //onView(withId(R.id.list_results)).perform();

        // Click the selection (somehow?)

        // Verify contents // this should be in a different Activity?
        // verify activity?

        // Hit back from menu bar

        //verify existing list
    }

    @Test
    public void verifyButtonShowsUpInUI() throws Exception {
        //// TODO: 11/16/17

        // no idea how to do this?

    }
}
