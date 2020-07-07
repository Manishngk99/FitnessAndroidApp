package com.example.fitnessclub.ui.home;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessclub.CreateChannel.Channel;
import com.example.fitnessclub.activities.DashboardActivity;
import com.example.fitnessclub.activities.ListExerciseActivity;
import com.example.fitnessclub.activities.LoginActivity;
import com.example.fitnessclub.R;
import com.example.fitnessclub.activities.ProductsActivity;
import com.example.fitnessclub.adapter.HomeAdapter;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.HomeContentModel;
import com.example.fitnessclub.model.Product;
import com.example.fitnessclub.ui.dashboard.DashboardFragment;
import com.example.fitnessclub.url.Url;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.SENSOR_SERVICE;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    //    List<HomeContentModel> homeAdapterList = new ArrayList<>();
    private Button btnHomeProducts;
    private ImageButton ibExerciseList,ibIcon2,ibIcon3;

    private SensorManager sensorManager;

    private int[] imageList = new int[]{
            R.drawable.one, R.drawable.two, R.drawable.three
    };

    private String[] imageTile = new String[]{
            "one", "two", "three"
    };



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnHomeProducts = root.findViewById(R.id.btnHomeProducts);
        recyclerView = root.findViewById(R.id.recycleView);
        ibExerciseList = root.findViewById(R.id.ibExerciseList);
        ibIcon2 = root.findViewById(R.id.btnIcon2);


        proximity();

        btnHomeProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentProductList();
            }
        });

        ibExerciseList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentListExercise();
            }
        });
        ibIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentProductList();

            }
        });



        CarouselView carouselView = root.findViewById(R.id.carouselView);
        carouselView.setPageCount(imageList.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imageList[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), imageTile[position], Toast.LENGTH_SHORT).show();
            }
        });

        //function call for recycle view data.
        initData();

        return root;
    }

    private void initData() {

        UserApi userApi = Url.getInstance().create(UserApi.class);

        final Call<List<HomeContentModel>> productCall = userApi.getProductDetail();

        productCall.enqueue(new Callback<List<HomeContentModel>>() {
            @Override
            public void onResponse(Call<List<HomeContentModel>> call, Response<List<HomeContentModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<HomeContentModel> homeContentModel = response.body();
                HomeAdapter homeAdapter = new HomeAdapter(getContext(),homeContentModel);
                recyclerView.setAdapter(homeAdapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

            }

            @Override
            public void onFailure(Call<List<HomeContentModel>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intentProductList() {
        Intent intent = new Intent(getActivity(), ProductsActivity.class);
        startActivity(intent);
    }

    private void intentListExercise() {
        Intent intent = new Intent(getActivity(), ListExerciseActivity.class);
        startActivity(intent);
    }

    private void proximity() {
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener proxilistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] == 0) {
                    Intent intent=new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if(sensor != null) {
            sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        }
        else {
            Toast.makeText(getContext(), "sensor not found", Toast.LENGTH_SHORT).show();
        }
    }
}

