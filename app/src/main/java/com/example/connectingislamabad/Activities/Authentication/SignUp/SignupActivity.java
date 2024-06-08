package com.example.connectingislamabad.Activities.Authentication.SignUp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.connectingislamabad.Activities.Authentication.SignIn.SigninActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEt, emailEt, passwordEt, confirmpasswordEt;
    private TextView signInRedirect;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signInRedirect = findViewById(R.id.signInRedirect);

        nameEt = findViewById(R.id.name);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password); // Assuming you have a password EditText with id 'password'
        confirmpasswordEt = findViewById(R.id.confirmpassword);
        submitButton = findViewById(R.id.signupBtn);

        // On Click on Sign up Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    processFormFields();
                }
            }
        });

        // TextView  Redirect to SignIn Class
        signInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Form validation
    private boolean validateForm() {
        String name = nameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();
        String confirmPassword = confirmpasswordEt.getText().toString().trim();

        if (name.isEmpty()) {
            nameEt.setError("Name is required");
            nameEt.requestFocus();
            return false;
        }

        if (!name.matches("[a-zA-Z ]+")) {
            nameEt.setError("Name can only contain letters and spaces");
            nameEt.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            emailEt.setError("Email is required");
            emailEt.requestFocus();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEt.setError("Please enter a valid email");
            emailEt.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            passwordEt.setError("Password is required");
            passwordEt.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            passwordEt.setError("Password should be at least 6 characters long");
            passwordEt.requestFocus();
            return false;
        }

        if (confirmPassword.isEmpty()) {
            confirmpasswordEt.setError("Confirm Password is required");
            confirmpasswordEt.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmpasswordEt.setError("Passwords do not match");
            confirmpasswordEt.requestFocus();
            return false;
        }

        return true;
    }

    //Calling Local Ip to Insert Into Database

        // Request To Server
        private void processFormFields() {
            // Request To Server
            RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);

            // URL to post at
            String url = "http://192.168.10.8:8080/api/v2/user/register";

            // String Request Object
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if ("Email already exists".equals(response.trim())) {
                        emailEt.setError("Email already exists");
                        emailEt.requestFocus(); // Focus on the email field to prompt the user to fix the error
                    } else if ("User Registered".equals(response.trim())) {
                        // Handle successful registration here
                        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("is_logged_in", true);
                        editor.apply();

                        // Clear form fields
                        nameEt.setText(null);
                        emailEt.setText(null);
                        passwordEt.setText(null);

                        Toast.makeText(SignupActivity.this, "Registration done", Toast.LENGTH_SHORT).show();

                        // Redirect to MainActivity or any other desired activity
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignupActivity.this, "Unsuccessful: " + error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", nameEt.getText().toString().trim());
                    params.put("email", emailEt.getText().toString().trim());
                    params.put("password", passwordEt.getText().toString().trim());
                    return params;
                }
            };
            queue.add(stringRequest);
        }

    }
