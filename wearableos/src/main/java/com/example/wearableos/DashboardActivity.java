package com.example.wearableos;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;

import com.example.wearableos.activities.ExerciseViewActivity;
import com.example.wearableos.activities.ListExerciseActivity;
import com.example.wearableos.activities.ProfileActivity;

public class DashboardActivity extends WearableActivity {
    private Button btnExercise,btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnProfile = findViewById(R.id.btnProfile);
        btnExercise = findViewById(R.id.btnExercise);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile();
            }
        });
        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exercise();
            }
        });

    }

    public void exercise(){
        Intent intent = new Intent(DashboardActivity.this, ListExerciseActivity.class);
        startActivity(intent);
    }
    public void profile(){
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        startActivity(intent);
    }
}
