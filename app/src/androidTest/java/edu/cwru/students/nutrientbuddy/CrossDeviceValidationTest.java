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
public class CrossDeviceValidationTest {

    @Rule
    public ActivityTestRule<SearchScreenActivity> mActivityRule = new ActivityTestRule<>(
            SearchScreenActivity.class);

    @Test
    public void verifySomething() throws Exception {
        //// TODO: 11/16/17
    }

}
