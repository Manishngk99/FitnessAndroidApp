package com.example.fitnessclub.api;

import com.example.fitnessclub.model.CatagoryModel;
import com.example.fitnessclub.model.HomeContentModel;
import com.example.fitnessclub.model.Product;
import com.example.fitnessclub.model.UpdateUserModel;
import com.example.fitnessclub.model.UserDataModel;
import com.example.fitnessclub.model.UserModel;
import com.example.fitnessclub.serverresponse.ImageResponse;

import com.example.fitnessclub.serverresponse.SignupResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserApi {

    @POST("/registration")
    Call<SignupResponse> registerUser(@Body UserModel userModel);

    @Multipart
    @POST("/user/imageUpload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @FormUrlEncoded
    @POST("user/login")
    Call<SignupResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @GET("getUser")
    Call<UserDataModel> displayUser(@Header("Authorization") String token);

    @GET("getProduct")
    Call<List<HomeContentModel>> getProductDetail();

    @PUT("user/userUpdate")
    Call<Void> updateUser(@Header("Authorization") String token, @Body UpdateUserModel updateUserModel);

    @PUT("addToCart/{id}")
    Call<HomeContentModel> addCart(@Path("id") int id, @Header("Authorization") String token);

    @GET("getProductCart")
    Call<List<HomeContentModel>> getCart(@Header("Authorization") String token);

    @GET("getProductByCategory/{id}")
    Call<List<CatagoryModel>> getProductByCategory(@Path("id")String id);

}