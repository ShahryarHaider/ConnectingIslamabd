package com.example.connectingislamabad.Activities.RetroFit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/v2/user/register")
    Call<User> createUser(@Body User user);
}
