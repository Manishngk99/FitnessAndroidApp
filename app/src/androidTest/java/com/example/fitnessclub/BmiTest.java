package com.example.fitnessclub;

import com.example.fitnessclub.ui.dashboard.DashboardFragment;
import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

public class BmiTest {

    @Test
    public void testBmiValue(){

        DashboardFragment dashboardFragment = new DashboardFragment();
               String expectedResult = "Normal";
//        Float actualResult = dashboardFragment.interpretBMI("16.15678");
//        assertEquals(expectedResult,actualResult);
    }
}
