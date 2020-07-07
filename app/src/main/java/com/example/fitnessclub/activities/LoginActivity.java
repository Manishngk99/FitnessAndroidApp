package com.example.fitnessclub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessclub.CreateChannel.Channel;
import com.example.fitnessclub.R;
import com.example.fitnessclub.StrictModeClass.StrictMode;
import com.example.fitnessclub.bll.LoginBLL;

import com.example.fitnessclub.notification.NotificationReciver;

public class LoginActivity extends AppCompatActivity  {


    private Button btnLogin;
    private ImageView ivImage;
    private EditText etusername, etpassword;
    private TextView tvSignup;
    private SensorManager sensorManager;
    boolean isSucess = false;
    private CheckBox cbRememberMe;
    public NotificationManagerCompat notificationManagerCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        Channel channel = new Channel(this);
        channel.createChannel();

        //binding
        btnLogin = findViewById(R.id.btnLogin);
        etusername = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);
        ivImage = findViewById(R.id.ivLogo);
        tvSignup = findViewById(R.id.tvSignup);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        sensorGyroscope();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onClick(View v) {
//
//        if (etusername.getText().toString().equals("admin")
//        && etpassword.getText().toString().equals("admin")){
//
//
//            SharedPreferences sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//
//            editor.putString("username",etusername.getText().toString());
//            editor.putString("password",etpassword.getText().toString());
//            editor.commit();
//
//            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Invalid detail", Toast.LENGTH_SHORT).show();
//        }
//
//    }

    public void login(){
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        if (TextUtils.isEmpty(etusername.getText().toString())) {
            etusername.setError("Please enter your username");
            return;
        } else if (TextUtils.isEmpty(etpassword.getText().toString())) {
            etpassword.setError("Please enter your email");
            return;
        }

        StrictMode.StrictMode();

        if (loginBLL.checkUser(username,password) == true){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            if (cbRememberMe.isChecked()){
                SaveUsers();
            }
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etusername.requestFocus();
        }

    }

    public void SaveUsers(){

        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", etusername.getText().toString());
        editor.putString("password", etpassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "User Saved", Toast.LENGTH_SHORT).show();
    }

    private void notifiy() {
        Notification notification = new NotificationCompat.Builder(this, Channel.CHANNEL_1)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark_normal_background)
                .setContentTitle("Fitness Club")
                .setContentText("Login success :" + etusername.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1, notification);
    }

    NotificationReciver notificationReciver = new NotificationReciver(this);

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(notificationReciver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(notificationReciver);
    }

    private void sensorGyroscope() {

            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                if (event.values[1] < 0) {
                    login();
                    finish();

                } else if (event.values[1] > 0) {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }
    }
}
