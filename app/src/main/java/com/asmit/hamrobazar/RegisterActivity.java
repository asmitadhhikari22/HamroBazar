package com.asmit.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asmit.hamrobazar.api.UsersAPI;
import com.asmit.hamrobazar.model.User;
import com.asmit.hamrobazar.serverresponse.ImageResponse;
import com.asmit.hamrobazar.serverresponse.SignUpresponse;
import com.asmit.hamrobazar.strictmode.StrictModeClass;
import com.asmit.hamrobazar.url.Url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private EditText etEmail,etFullname,etPw,etRePassword,etPhone,etMobilePhone,etStreetname,etLocation,etAddress3;
    private Button btnRegister;
    private CircleImageView Profile;
    String imagePath;
    private String imageName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Profile=findViewById(R.id.Profile);
        etEmail=findViewById(R.id.etEmail);
        etPw=findViewById(R.id.etPw);
        etFullname=findViewById(R.id.etFullname);
        etRePassword=findViewById(R.id.etRePassword);
        etPhone=findViewById(R.id.etPhone);
        etMobilePhone=findViewById(R.id.etMobilePhone);
        etStreetname=findViewById(R.id.etStreetname);
        etLocation=findViewById(R.id.etLocation);
        etAddress3=findViewById(R.id.etAddress3);
        btnRegister=findViewById(R.id.btnRegister);

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPw.getText().toString().equals(etRePassword.getText().toString())){
                    if (validate()){
                        saveImageOnly();
                        signUp();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password doesnot match", Toast.LENGTH_SHORT).show();
                    etPw.requestFocus();
                    return;
                }
            }
        });
    }

    private boolean validate() {
        boolean status=true;
        if (etMobilePhone.getText().toString().length()==9 && etPhone.getText().toString().length()==6 ){
            etPhone.setError("7 number required");
            etMobilePhone.setError("10 number require");
            status =false;
        }
        return status;
    }
    private void BrowseImage() {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
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
        Profile.setImageURI(uri);
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

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void signUp() {
        String email=etEmail.getText().toString();
        String fullname=etFullname.getText().toString();
        String password=etPw.getText().toString();
        String phone=etPhone.getText().toString();
        String mobilephone=etMobilePhone.getText().toString();
        String streetname=etStreetname.getText().toString();
        String location=etLocation.getText().toString();
        String address3=etAddress3.getText().toString();

        User users= new User(email,fullname,password,phone,mobilephone,streetname,location,address3,imageName);
        UsersAPI usersAPI= Url.getInstance().create(UsersAPI.class);
        Call<SignUpresponse> signUpCall=usersAPI.registerUser(users);

        signUpCall.enqueue(new Callback<SignUpresponse>() {
            @Override
            public void onResponse(Call<SignUpresponse> call, Response<SignUpresponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Register sucessfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUpresponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }





}
