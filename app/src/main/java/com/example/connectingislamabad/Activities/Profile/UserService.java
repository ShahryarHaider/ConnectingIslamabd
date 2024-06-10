package com.example.connectingislamabad.Activities.Profile;

import com.example.connectingislamabad.Activities.RetroFit.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;

public interface UserService {
    @PATCH("users/{id}")
    Call<User> updateUserProfile(@Body User user);
}