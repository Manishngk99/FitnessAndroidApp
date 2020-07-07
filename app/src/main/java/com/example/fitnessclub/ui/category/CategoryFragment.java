package com.example.fitnessclub.ui.category;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitnessclub.R;
import com.example.fitnessclub.adapter.CatagoryAdapter;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.CatagoryModel;
import com.example.fitnessclub.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private Button btn1, btn2, btn3;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.category_fragment, container, false);


        btn1 = root.findViewById(R.id.btnEquipment);
        btn2 = root.findViewById(R.id.btnDiet);
        btn3 = root.findViewById(R.id.btnHerbs);
        recyclerView = root.findViewById(R.id.rvCategory);

        final UserApi userApi = Url.getInstance().create(UserApi.class);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Call<List<CatagoryModel>> categoryDetailsCall = userApi.getProductByCategory("1");

                categoryDetailsCall.enqueue(new Callback<List<CatagoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CatagoryModel>> call, Response<List<CatagoryModel>> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            List<CatagoryModel> categoryDetailsList = response.body();

                            CatagoryAdapter categoryAdapter = new CatagoryAdapter("Equipment",getContext(), categoryDetailsList, getFragmentManager() );
                            recyclerView.setAdapter(categoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CatagoryModel>> call, Throwable t) {
                        Log.d("Error: ", t.getLocalizedMessage());
                    }
                });

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Call<List<CatagoryModel>> categoryDetailsCall = userApi.getProductByCategory("3");

                categoryDetailsCall.enqueue(new Callback<List<CatagoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CatagoryModel>> call, Response<List<CatagoryModel>> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            List<CatagoryModel> categoryDetailsList = response.body();

                            CatagoryAdapter categoryAdapter = new CatagoryAdapter("Diet",getContext(), categoryDetailsList, getFragmentManager() );
                            recyclerView.setAdapter(categoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CatagoryModel>> call, Throwable t) {
                        Log.d("Error: ", t.getLocalizedMessage());
                    }
                });

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Call<List<CatagoryModel>> categoryDetailsCall = userApi.getProductByCategory("2");

                categoryDetailsCall.enqueue(new Callback<List<CatagoryModel>>() {
                    @Override
                    public void onResponse(Call<List<CatagoryModel>> call, Response<List<CatagoryModel>> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            List<CatagoryModel> categoryDetailsList = response.body();

                            CatagoryAdapter categoryAdapter = new CatagoryAdapter("Herbs",getContext(), categoryDetailsList, getFragmentManager() );
                            recyclerView.setAdapter(categoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CatagoryModel>> call, Throwable t) {
                        Log.d("Error: ", t.getLocalizedMessage());
                    }
                });

            }
        });

        return root;
    }



}
