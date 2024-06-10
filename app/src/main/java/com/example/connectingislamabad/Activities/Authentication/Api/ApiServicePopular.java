package com.example.connectingislamabad.Activities.Authentication.Api;

import com.example.connectingislamabad.Domains.PopularDomain;

import retrofit2.http.GET;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServicePopular {
    @GET("/popular")
    Call<List<PopularDomain>> getPopularDomains();

}
