package com.example.connectingislamabad.Activities.Authentication.Api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Adapters.Review;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

public class RestClient {

    private RequestQueue queue;

    public RestClient(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void fetchReviews(final Callback callback) {
        String url = "http://localhost:8080/review";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Deserialize JSON array to List<Review>
                            Gson gson = new Gson();
                            Type reviewListType = new TypeToken<List<Review>>() {}.getType();
                            List<Review> reviews = gson.fromJson(response.toString(), reviewListType);
                            Log.d("Error", reviews.toString());
                            callback.onResponse(reviews);
                        } catch (Exception e) {
                            e.printStackTrace();
                            callback.onFailure();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.onFailure();
            }
        });

        queue.add(jsonArrayRequest);
    }

    public interface Callback {
        void onResponse(List<Review> reviews);
        void onFailure();
    }
}
