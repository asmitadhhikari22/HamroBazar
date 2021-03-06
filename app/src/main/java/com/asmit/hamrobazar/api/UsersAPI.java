package com.asmit.hamrobazar.api;

import com.asmit.hamrobazar.ListedAds;
import com.asmit.hamrobazar.model.User;
import com.asmit.hamrobazar.serverresponse.ImageResponse;
import com.asmit.hamrobazar.serverresponse.SignUpResponse;

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
import retrofit2.http.Part;

public interface UsersAPI {
    @POST("users/signup")
    Call<SignUpResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignUpResponse> checkUser(@Field ("email") String email, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);



    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization") String token);

    @GET("listedads")
    Call<List<ListedAds>>getListedAds();
}
