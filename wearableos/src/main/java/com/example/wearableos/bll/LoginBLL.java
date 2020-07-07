package com.example.wearableos.bll;

import com.example.wearableos.api.UserApi;
import com.example.wearableos.serverresponse.SignupResponse;
import com.example.wearableos.url.Url;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {
    boolean isSuccess = false;

    public boolean checkUser(String username, String password){

        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<SignupResponse> signupResponseCall = userApi.checkUser(username,password);
        try{
            Response<SignupResponse> signupResponseResponse = signupResponseCall.execute();
            if (signupResponseResponse.isSuccessful()){
                signupResponseResponse.body().getStatus().equals("200");
                Url.token += signupResponseResponse.body().getToken();
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
