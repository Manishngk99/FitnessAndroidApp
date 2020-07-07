package com.example.fitnessclub.ui.dashboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.activities.LoginActivity;
import com.example.fitnessclub.R;
import com.example.fitnessclub.activities.ProductsActivity;
import com.example.fitnessclub.activities.UpdateProfileActivity;
import com.example.fitnessclub.adapter.CartAdapter;
import com.example.fitnessclub.adapter.HomeAdapter;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.HomeContentModel;
import com.example.fitnessclub.model.UserDataModel;
import com.example.fitnessclub.url.Url;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.SENSOR_SERVICE;

public class DashboardFragment extends Fragment {

    private Button btnLogout,btnEditProfile;
    private TextView tvDashboardName,tvWeight,tvHeight,tvCatagory;
    private ImageView ivDashboardProfile;
    private RecyclerView rvDashboard;
    private SensorManager sensorManager;
    private static final int SHAKE_THRESHOLD = 800;
    


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        tvDashboardName = root.findViewById(R.id.tvDashboardName);
        tvHeight = root.findViewById(R.id.ivDHeight);
        tvWeight = root.findViewById(R.id.tvDWeight);
        tvCatagory = root.findViewById(R.id.tvDCatagory);
        ivDashboardProfile = root.findViewById(R.id.ivDashboardProfile);
        btnLogout = root.findViewById(R.id.btnDLogout);
        btnEditProfile = root.findViewById(R.id.btnDEdit);
        rvDashboard = root.findViewById(R.id.rvDashboard);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToUpdateUser();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        displayUserData();
        getCart();


        return root;
    }

    public void logout(){
        File sharedPreferenceFile = new File("/data/data/" + getActivity().getPackageName() + "/shared_prefs/");
        File[] listFiles = sharedPreferenceFile.listFiles();
        for (File file : listFiles) {
            file.delete();
        }
        Url.token = "Bearer ";
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }



    public void redirectToUpdateUser(){
        Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
        startActivity(intent);
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

        private void getCart(){

            UserApi userApi = Url.getInstance().create(UserApi.class);

            final Call<List<HomeContentModel>> cartProductCall = userApi.getCart(Url.token);

            cartProductCall.enqueue(new Callback<List<HomeContentModel>>() {
                @Override
                public void onResponse(Call<List<HomeContentModel>> call, Response<List<HomeContentModel>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    List<HomeContentModel> homeContentModel = response.body();
                    CartAdapter cartAdapter = new CartAdapter(getContext(),homeContentModel);
                    rvDashboard.setAdapter(cartAdapter);
                    rvDashboard.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                }

                @Override
                public void onFailure(Call<List<HomeContentModel>> call, Throwable t) {
                    Toast.makeText(getContext(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }



        public void systAcc(){

            sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            SensorEventListener sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    float[] values = sensorEvent.values;

                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }



}