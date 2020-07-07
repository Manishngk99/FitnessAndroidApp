package com.example.fitnessclub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.fitnessclub.R;
import com.example.fitnessclub.adapter.HomeAdapter;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.HomeContentModel;
import com.example.fitnessclub.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        recyclerView = findViewById(R.id.recycleViewProducts);

        UserApi userApi = Url.getInstance().create(UserApi.class);

        final Call<List<HomeContentModel>> productCall = userApi.getProductDetail();

        productCall.enqueue(new Callback<List<HomeContentModel>>() {
            @Override
            public void onResponse(Call<List<HomeContentModel>> call, Response<List<HomeContentModel>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProductsActivity.this, "Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<HomeContentModel> homeContentModel = response.body();
                HomeAdapter homeAdapter = new HomeAdapter(ProductsActivity.this, homeContentModel);
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setLayoutManager(new GridLayoutManager(ProductsActivity.this,2));
            }

            @Override
            public void onFailure(Call<List<HomeContentModel>> call, Throwable t) {
                Toast.makeText(ProductsActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
