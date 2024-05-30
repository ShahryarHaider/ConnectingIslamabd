package com.example.connectingislamabad.Activities.Authentication.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Interface {
    @FormUrlEncoded
    @POST("adduser.php") //php file
    Call<Response>adduser(@Field("fullname")String fullname,@Field("email")String email,@Field("password")String password);
}
