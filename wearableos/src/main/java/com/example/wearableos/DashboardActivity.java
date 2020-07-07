package com.example.wearableos;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;

public class DashboardActivity extends WearableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}
