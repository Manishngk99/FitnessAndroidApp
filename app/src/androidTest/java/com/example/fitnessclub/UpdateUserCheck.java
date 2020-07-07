package com.example.fitnessclub;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.fitnessclub.activities.UpdateProfileActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UpdateUserCheck {
    @Rule
    public ActivityTestRule<UpdateProfileActivity> checkActivity
            = new ActivityTestRule<>(UpdateProfileActivity.class);

    @Test
    public void TestUI() throws Exception{
        onView(withId(R.id.etUFullname)).perform(typeText("Manish"));
        onView(withId(R.id.etUAdress)).perform(typeText("ktm"));
        onView(withId(R.id.etUPhonenumber)).perform(typeText("876123"));
        onView(withId(R.id.etUWeight)).perform(typeText("56"));
        onView(withId(R.id.etUHeight)).perform(typeText("873"));


        onView(withId(R.id.btnUUpdate)).check(matches(withText("Registered")));
    }
}
