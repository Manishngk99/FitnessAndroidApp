package com.example.wearableos.api;

import com.example.wearableos.serverresponse.SignupResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;


public interface UserApi {


    @FormUrlEncoded
    @POST("user/login")
    Call<SignupResponse> checkUser(@Field("username") String username, @Field("password") String password);


}