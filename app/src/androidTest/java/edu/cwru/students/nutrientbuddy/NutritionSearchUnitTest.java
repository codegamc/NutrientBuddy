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
public class NutritionSearchUnitTest {

    // remember:
    // Context appContext = InstrumentationRegistry.getTargetContext();

    //http://www.vogella.com/tutorials/AndroidTestingEspresso/article.html

    private String validFood = "apple";
    private String emptyStr = "";
    private String invalidStr = "12 % ^ / $(cd ..)";

    @Rule
    public ActivityTestRule<SearchScreenActivity> mActivityRule = new ActivityTestRule<>(
            SearchScreenActivity.class);

    @Test
    public void searchValidFoodString() throws Exception {
        // Search with valid food string value.
        onView(withId(R.id.search_text_area)).perform(typeText(validFood), closeSoftKeyboard());
        // should something else happen?
        // This has to simply not throw an exception
        //onView(withId(R.id.search_results)).perform();
    }

    @Test
    public void verifyEmptyQuery() throws Exception {
        onView(withId(R.id.search_text_area)).perform(typeText(emptyStr), closeSoftKeyboard());
    }

    @Test
    public void searchWithInvalidValue() throws Exception {
        // proof of failure
        onView(withId(R.id.search_text_area)).perform(typeText(invalidStr), closeSoftKeyboard());
    }


}