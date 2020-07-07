package com.example.fitnessclub.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.activities.ExerciseViewActivity;
import com.example.fitnessclub.activities.ListExerciseActivity;
import com.example.fitnessclub.R;
import com.example.fitnessclub.model.ExercisesModel;

import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExercisesHolder> {

    ListExerciseActivity Context;
    List<ExercisesModel> exercisesModelList;


    public ExercisesAdapter(ListExerciseActivity context, List<ExercisesModel> exercisesModelList) {
        Context = context;
        this.exercisesModelList = exercisesModelList;
    }

    @NonNull
    @Override
    public ExercisesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exerciselist,parent,
                false);
        return new ExercisesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesHolder holder, int position) {
        final ExercisesModel exercisesModel = exercisesModelList.get(position);
        holder.tvExerciseInfo.setText(exercisesModel.getName());
        holder.ibExercise.setImageResource(exercisesModel.getImage_id());

        holder.ibExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Context, ExerciseViewActivity.class);
                intent.putExtra("info",exercisesModel.getName());
                intent.putExtra("image",exercisesModel.getImage_id());

                Context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exercisesModelList.size();
    }

    class ExercisesHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvExerciseInfo;
        private ImageButton ibExercise;

        public ExercisesHolder(@NonNull View itemView) {
            super(itemView);
            tvExerciseInfo = itemView.findViewById(R.id.tvExerciseInfo);
            ibExercise = itemView.findViewById(R.id.ibExerciseImage);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
