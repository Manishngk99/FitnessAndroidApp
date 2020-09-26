package com.example.wearableos.activities;

import android.support.wearable.activity.WearableActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.wearableos.R;
import com.example.wearableos.adapter.ExercisesAdapter;
import com.example.wearableos.model.ExercisesModel;

import java.util.ArrayList;
import java.util.List;

public class ListExerciseActivity extends WearableActivity {
    private RecyclerView recyclerView;
    List<ExercisesModel> exercisesModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        recyclerView = findViewById(R.id.recycleViewExercise);
        initData();
        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(this,exercisesModelList);
        recyclerView.setAdapter(exercisesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void initData(){
        exercisesModelList.add(new ExercisesModel(R.drawable.easy_pose,"Easy Pose"));
        exercisesModelList.add(new ExercisesModel(R.drawable.boat_pose,"Boat Pose"));
        exercisesModelList.add(new ExercisesModel(R.drawable.bow_pose,"Bow Pose"));
        exercisesModelList.add(new ExercisesModel(R.drawable.cobra_pose,"Cobra Pose"));
        exercisesModelList.add(new ExercisesModel(R.drawable.crescent_lunge,"Crescent Lunge"));
        exercisesModelList.add(new ExercisesModel(R.drawable.easy_pose,"Easy Pose"));
    }
}