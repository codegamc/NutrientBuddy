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
public class MenuBarTest {

    @Rule
    public ActivityTestRule<SearchScreenActivity> mActivityRule = new ActivityTestRule<>(
            SearchScreenActivity.class);

    @Test
    public void verifyShoppingListNavigation() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifySearchNavigation() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifySavedRecipeNavigation() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifyHelpNavigation() throws Exception {
        //// TODO: 11/16/17
    }

    @Test
    public void verifyBackButtonNavigation() throws Exception {
        //// TODO: 11/16/17
    }
}