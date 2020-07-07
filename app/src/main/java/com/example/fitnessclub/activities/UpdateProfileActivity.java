package com.example.fitnessclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessclub.R;
import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.model.UpdateUserModel;
import com.example.fitnessclub.model.UserDataModel;
import com.example.fitnessclub.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText etFullname, etAddress, etPhoneNumber, etWeight, etHeight;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);

        etFullname = findViewById(R.id.etUFullname);
        etAddress = findViewById(R.id.etUAdress);
        etPhoneNumber = findViewById(R.id.etUPhonenumber);
        etHeight = findViewById(R.id.etUHeight);
        etWeight = findViewById(R.id.etUWeight);
        btnUpdate = findViewById(R.id.btnUUpdate);

        final UserApi userApi = Url.getInstance().create(UserApi.class);
        final Call<UserDataModel> updateUserModelCall = userApi.displayUser(Url.token);
           updateUserModelCall.enqueue(new Callback<UserDataModel>() {
               @Override
               public void onResponse(Call<UserDataModel> call, Response<UserDataModel> response) {
                   if (!response.isSuccessful()) {
                       Toast.makeText(UpdateProfileActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                       return;
                   }
                   else {
                       etFullname.setText(response.body().getUsername());
                       etAddress.setText(response.body().getWeight());
                       etPhoneNumber.setText(response.body().getPhonenumber());
                       etHeight.setText(response.body().getHeight());
                       etWeight.setText(response.body().getWeight());
                   }
               }

               @Override
               public void onFailure(Call<UserDataModel> call, Throwable t) {
                   Log.d("Error: ", t.getLocalizedMessage());
               }
           });

           btnUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   final Call<Void> voidCall = userApi.updateUser(Url.token,
                           new UpdateUserModel(
                                   etFullname.getText().toString(),
                                   etAddress.getText().toString(),
                                   etPhoneNumber.getText().toString(),
                                   etWeight.getText().toString(),
                                   etHeight.getText().toString()
                                   ));
                   voidCall.enqueue(new Callback<Void>() {
                       @Override
                       public void onResponse(Call<Void> call, Response<Void> response) {
                           if(!response.isSuccessful()){
                               Toast.makeText(UpdateProfileActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                               return;
                           }
                           else {
                               Toast.makeText(UpdateProfileActivity.this, "User has been updated successfully.", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<Void> call, Throwable t) {

                       }
                   });
               }
           });

    }
}
