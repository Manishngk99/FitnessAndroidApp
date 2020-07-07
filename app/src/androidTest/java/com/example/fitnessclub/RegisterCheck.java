package com.example.fitnessclub;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.fitnessclub.activities.SignUpActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegisterCheck {
    private String textToBeType;


    @Rule
    public ActivityTestRule<SignUpActivity> checkActivity
            = new ActivityTestRule<>(SignUpActivity.class);

    @Before
    public void initValidString(){
        textToBeType = "Espresso";
    }

    @Test
    public void TestUI() throws Exception{
        onView(withId(R.id.etSFullname)).perform(replaceText("Manish Nagarkoti"));
        onView(withId(R.id.etSUsername)).perform(replaceText("manish"));
        onView(withId(R.id.etSAddress)).perform(replaceText("ktm"));
        onView(withId(R.id.etSPhonenumber)).perform(replaceText("67679"));
        onView(withId(R.id.etSPassword)).perform(replaceText("manish"));

        onView(withId(R.id.btnSSignup)).perform(ViewActions.closeSoftKeyboard());
    }
}
