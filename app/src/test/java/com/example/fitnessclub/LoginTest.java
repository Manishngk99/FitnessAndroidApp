package com.example.fitnessclub;

import com.example.fitnessclub.api.UserApi;
import com.example.fitnessclub.bll.LoginBLL;
import com.example.fitnessclub.model.UserModel;
import com.example.fitnessclub.serverresponse.SignupResponse;
import com.example.fitnessclub.url.Url;

import org.hamcrest.core.IsNull;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class LoginTest {


    @Test
    public void loginCheck() throws IOException
    {
        UserApi userAPI = Url.getInstance().create(UserApi.class);
        Call<SignupResponse> loginCall = userAPI.checkUser("manish", "manish");
        try {
            Response<SignupResponse> response = loginCall.execute();
            SignupResponse signUpResponse = response.body();
            assertTrue(response.isSuccessful() && signUpResponse.getStatus() == "200");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerCheck() throws IOException
    {
        UserApi userAPI = Url.getInstance().create(UserApi.class);
        Call<SignupResponse> registerCall = userAPI.registerUser(new UserModel("manish nagarkoti",
                "manishngk99", "manishngk99", "Ktm", "1234",
                null,null,null));
        try {
            Response<SignupResponse> response = registerCall.execute();
            String message = response.message();
            assertTrue(response.isSuccessful() && message == "User is sucessfully registered.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
