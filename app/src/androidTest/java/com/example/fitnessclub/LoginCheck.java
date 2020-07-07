package com.example.fitnessclub;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.fitnessclub.activities.LoginActivity;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginCheck {
    private String textToBeType;

    @Rule
    public ActivityTestRule<LoginActivity> checkActivity
            = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void initValidString(){
        textToBeType = "Espresso";
    }

    @Test
    public void LoginUITest() throws Exception{
        onView(withId(R.id.etUsername)).perform(replaceText("manish"));
        onView(withId(R.id.etPassword)).perform(replaceText("manish"));
        onView(withId(R.id.btnLogin)).perform(click());

    }
}
