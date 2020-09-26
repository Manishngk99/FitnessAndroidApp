package com.example.wearableos.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.wearable.activity.WearableActivity;

import com.example.wearableos.R;

public class ExerciseViewActivity extends WearableActivity {

    private ImageView ivExerciseImage;
    private TextView tvExerciseName,tvTimer;
    private Button btnStart;
    Boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_view);

        ivExerciseImage = findViewById(R.id.ivExerciseImage);
        tvExerciseName = findViewById(R.id.tvNameOfExercise);
        tvTimer = findViewById(R.id.tvTimer);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning){

                    btnStart.setText("DONE");
                    new CountDownTimer(20000, 1000) {
                        @Override
                        public void onTick(long l) {
                            tvTimer.setText(""+l/1000);
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ExerciseViewActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                } else {
                    Toast.makeText(ExerciseViewActivity.this, "Finish", Toast.LENGTH_SHORT).show();
                    finish();
                }
                isRunning = !isRunning;
            }
        });

        tvTimer.setText("");

        Bundle bundle  = getIntent().getExtras();

        if (bundle!= null){
            ivExerciseImage.setImageResource(bundle.getInt("image"));
            tvExerciseName.setText(bundle.getString("info"));
        }
    }
}
