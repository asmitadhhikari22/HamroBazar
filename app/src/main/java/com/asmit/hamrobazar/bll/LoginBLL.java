package com.asmit.hamrobazar.bll;

import com.asmit.hamrobazar.api.UsersAPI;
import com.asmit.hamrobazar.serverresponse.SignUpResponse;
import com.asmit.hamrobazar.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBLL {

    boolean isSucess=false;

    public boolean checkUser(String email,String password){
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> usersCall = usersAPI.checkUser(email, password);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                isSucess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSucess;
    }
}
