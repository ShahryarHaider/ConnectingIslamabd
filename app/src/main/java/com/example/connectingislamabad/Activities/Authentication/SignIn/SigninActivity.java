package com.example.connectingislamabad.Activities.Authentication.SignIn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailEditText = findViewById(R.id.emailForm);
        passwordEditText = findViewById(R.id.passwordForm);
        signinButton = findViewById(R.id.signinButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    login();
                }
            }
        });
    }

    // Form validation
    private boolean validateForm() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email");
            emailEditText.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Password should be at least 6 characters long");
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }

    // Method to handle login logic
    private void login() {
        // Request To Server
        RequestQueue queue = Volley.newRequestQueue(SigninActivity.this);

        // URL to post at
        String url = "http://192.168.10.13:8080/api/v2/user/login"; // Use your server's IP address

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email",emailEditText.getText().toString());
        params.put("password",passwordEditText.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Values From Response
                            String name = (String) response.get("name");
                            String email = (String) response.get("email");

                            // Save login state in SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("is_logged_in", true);
                            editor.putString("name", name);
                            editor.putString("email", email);
                            editor.apply();

                            // Set Intent Actions
                            Intent mainActivity = new Intent(SigninActivity.this, MainActivity.class);
                            mainActivity.putExtra("name",name);
                            mainActivity.putExtra("email",email);

                            startActivity(mainActivity);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SigninActivity.this, "Unsuccessful: " + error.toString(), Toast.LENGTH_LONG).show();

            }
        });
        queue.add(jsonObjectRequest);
    }
}
