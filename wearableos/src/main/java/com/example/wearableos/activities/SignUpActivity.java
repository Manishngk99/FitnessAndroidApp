package com.example.wearableos.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.support.wearable.activity.WearableActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import com.example.wearableos.R;
import com.example.wearableos.StrictModeClass.StrictMode;
import com.example.wearableos.api.UserApi;
import com.example.wearableos.model.UserModel;
import com.example.wearableos.serverresponse.ImageResponse;
import com.example.wearableos.serverresponse.SignupResponse;
import com.example.wearableos.url.Url;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends WearableActivity {

    private ImageButton ivSImage;
    private EditText etfullname, etaddress, etpassword, etusername, etphonenumber, etWeight, etHeight;
    private Button btnSignup;
    private String imagePath, imageName;
//    private String userChooseTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //user permission to access camera
        checkPermission();

        ivSImage = findViewById(R.id.ivSignup);
        etfullname = findViewById(R.id.etSFullname);
        etusername = findViewById(R.id.etSUsername);
        etpassword = findViewById(R.id.etSPassword);
        etaddress = findViewById(R.id.etSAddress);
        etphonenumber = findViewById(R.id.etSPhonenumber);
        etWeight = findViewById(R.id.etSWeight);
        etHeight = findViewById(R.id.etSHeight);
        btnSignup = findViewById(R.id.btnSSignup);

        ivSImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                selectImage();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImageOnly() ;
                registration();
            }
        });
    }

    //for selecting image

    private void selectImage() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("Select Profile Image");
        String[] imageDialogItems = {
                "Select from gallery",
                "Take picture"
        };
        alertBuilder.setItems(imageDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                BrowseImage();
                                break;
                            case 1:
                                loadCamera();
                                break;
                        }
                    }
                });
        alertBuilder.show();
    }

    //to load local storage
    public void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    //to load camera function
    private void loadCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(SignUpActivity.this.getPackageManager())!= null){
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        ivSImage.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 0);
        }
    }

    private void saveImageOnly(){
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(),requestBody);
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<ImageResponse> imageResponseCall = userApi.uploadImage(body);

        StrictMode.StrictMode();
        //Synchronous method
        try{
            Response<ImageResponse> imageResponseResponse = imageResponseCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image is inserted" + imageName, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, "Error in image insert" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    //for registration
    public void registration(){
        String fname = etfullname.getText().toString();
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        String address = etaddress.getText().toString();
        String phonenumber = etphonenumber.getText().toString();
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();

        UserModel userModel = new UserModel(fname, username, password, address, phonenumber, imageName, weight, height);
        UserApi userApi = Url.getInstance().create(UserApi.class);
        Call<SignupResponse> signupResponseCall = userApi.registerUser(userModel);

        signupResponseCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(SignUpActivity.this, "Registered Successfull", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
