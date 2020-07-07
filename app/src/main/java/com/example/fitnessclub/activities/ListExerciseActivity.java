package com.example.fitnessclub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fitnessclub.R;
import com.example.fitnessclub.adapter.ExercisesAdapter;
import com.example.fitnessclub.model.ExercisesModel;

import java.util.ArrayList;
import java.util.List;

public class ListExerciseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<ExercisesModel> exercisesModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

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
