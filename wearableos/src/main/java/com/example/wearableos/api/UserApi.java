package com.example.wearableos.api;

import com.example.wearableos.serverresponse.SignupResponse;
import com.example.wearableos.serverresponse.ImageResponse;
import com.example.wearableos.model.UserModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface UserApi {


    @FormUrlEncoded
    @POST("user/login")
    Call<SignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @Multipart
    @POST("/user/imageUpload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @POST("/registration")
    Call<SignupResponse> registerUser(@Body UserModel userModel);
}