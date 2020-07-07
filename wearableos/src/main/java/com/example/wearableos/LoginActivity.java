package com.example.wearableos;

import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wearableos.StrictModeClass.StrictMode;
import com.example.wearableos.bll.LoginBLL;


public class LoginActivity extends WearableActivity {

    private Button btnLogin;
    private EditText etusername, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //binding
        btnLogin = findViewById(R.id.btnLogin);
        etusername = findViewById(R.id.etUsername);
        etpassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
         }


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
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etusername.requestFocus();
        }

    }
}
