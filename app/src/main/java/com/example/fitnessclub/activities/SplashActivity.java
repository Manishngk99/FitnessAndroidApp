package com.example.fitnessclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.fitnessclub.R;
import com.example.fitnessclub.StrictModeClass.StrictMode;
import com.example.fitnessclub.bll.LoginBLL;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
                finish();
            }
        }, 2000);
    }

    private void checkUser(){

        SharedPreferences sharedPreferences = getSharedPreferences("users",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password", "");

        StrictMode.StrictMode();

        LoginBLL loginBLL = new LoginBLL();

        if (loginBLL.checkUser(username, password)){
            Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();

        }  else {
            Intent intent1 = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent1);

        }
    }
}
