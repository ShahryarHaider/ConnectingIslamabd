package com.example.connectingislamabad.Activities.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private EditText newNameEt, newEmailEt, newPasswordEt;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        newNameEt = findViewById(R.id.newName);
        newEmailEt = findViewById(R.id.newEmail);
        newPasswordEt = findViewById(R.id.newPassword);
        updateButton = findViewById(R.id.updateBtn);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    updateUserProfile();
                }
            }
        });
    }

    // Form validation
    private boolean validateForm() {
        String name = newNameEt.getText().toString().trim();
        String email = newEmailEt.getText().toString().trim();
        String password = newPasswordEt.getText().toString().trim();

        if (name.isEmpty()) {
            newNameEt.setError("Name is required");
            newNameEt.requestFocus();
            return false;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            newNameEt.setError("Name can only contain letters and spaces");
            newNameEt.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            newEmailEt.setError("Email is required");
            newEmailEt.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            newEmailEt.setError("Please enter a valid email");
            newEmailEt.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            newPasswordEt.setError("Password is required");
            newPasswordEt.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            newPasswordEt.setError("Password should be at least 6 characters long");
            newPasswordEt.requestFocus();
            return false;
        }

        return true;
    }
    //Calling Local Ip to Update Into Database

    // Request To Server
    private void updateUserProfile() {
        RequestQueue queue = Volley.newRequestQueue(EditProfileActivity.this);

        String url = "http://192.168.10.31:8080/api/v2/user/update";

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("jwt_token", null);
        String userId = sharedPreferences.getString("user_id", null);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if ("User profile updated".equals(response.trim())) {
                    Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();

                    // Update shared preferences if necessary
                    SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", newNameEt.getText().toString().trim());
                    editor.putString("email", newEmailEt.getText().toString().trim());
                    editor.apply();
                } else {
                    Toast.makeText(EditProfileActivity.this, response, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProfileActivity.this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", userId);  // Include the user ID here
                params.put("name", newNameEt.getText().toString().trim());
                params.put("email", newEmailEt.getText().toString().trim());
                params.put("password", newPasswordEt.getText().toString().trim());
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        queue.add(stringRequest);
    }

}