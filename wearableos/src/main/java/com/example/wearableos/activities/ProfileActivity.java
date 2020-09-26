package com.example.wearableos.activities;

import android.support.wearable.activity.WearableActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wearableos.R;
import com.example.wearableos.api.UserApi;
import com.example.wearableos.model.UserDataModel;
import com.example.wearableos.url.Url;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends WearableActivity {
    private TextView tvDashboardName,tvWeight,tvHeight,tvCatagory;
    private ImageView ivDashboardProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvDashboardName = findViewById(R.id.tvDashboardName);
        tvHeight = findViewById(R.id.ivDHeight);
        tvWeight = findViewById(R.id.tvDWeight);
        tvCatagory = findViewById(R.id.tvDCatagory);
        ivDashboardProfile = findViewById(R.id.ivDashboardProfile);

        displayUserData();
    }

    public void displayUserData() {

        UserApi userApi = Url.getInstance().create(UserApi.class);
        final Call<UserDataModel> userCall = userApi.displayUser(Url.token);

        userCall.enqueue(new Callback<UserDataModel>() {
            @Override
            public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {
                tvDashboardName.setText(response.body().getUsername());
                tvHeight.setText(response.body().getHeight());
                tvWeight.setText(response.body().getWeight());
                Picasso.get().load(Url.imagePath + response.body().getProfileimage())
                        .centerCrop()
                        .resize(1024, 768)
                        .into(ivDashboardProfile);

                float weight = Float.parseFloat(response.body().getWeight());
                float height = Float.parseFloat(response.body().getHeight()) / 100;

                //Calculate BMI value
                float bmiValue = calculateBMI(weight, height);

                //Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);
                tvCatagory.setText(String.valueOf( bmiInterpretation));
            }

            @Override
            public void onFailure(Call<UserDataModel> call, Throwable t) {

            }
        });

    }

    //Calculate BMI
    public Float calculateBMI(Float weight, Float height) {
        return (float) (weight / (height * height));
    }

    // Interpret what BMI means
    public String interpretBMI(float bmiValue) {

        if (bmiValue < 16) {
            return "Severely underweight";
        } else if (bmiValue < 18.5) {

            return "Underweight";
        } else if (bmiValue < 25) {

            return "Normal";
        } else if (bmiValue < 30) {

            return "Overweight";
        } else {
            return "Obese";
        }
    }
}