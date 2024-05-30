package com.example.connectingislamabad.Activities.Authentication.SignUp;

import static android.app.ProgressDialog.show;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Activities.Authentication.Security.CustomNetworkSecurityPolicy;
import com.example.connectingislamabad.Activities.RetroFit.ApiClient;
import com.example.connectingislamabad.Activities.RetroFit.ApiService;
import com.example.connectingislamabad.Activities.RetroFit.User;
import com.example.connectingislamabad.Activities.RetroFit.UserRequestBody;
import com.example.connectingislamabad.Activities.RetroFit.UserService;
import com.example.connectingislamabad.R;

import retrofit2.Call;
import retrofit2.Callback;

import android.security.NetworkSecurityPolicy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText nameEt, emailEt, passwordEt;
    private Button submitButton;
    private ApiService apiService;
    private UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                    .detectCleartextNetwork()
//                    .penaltyLog()
//                    .build());
//
//            if (!NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
//                Log.w("NetworkSecurity", "Cleartext traffic not permitted");
//            }
//        }






        nameEt = findViewById(R.id.name);
        emailEt = findViewById(R.id.email);
        passwordEt = findViewById(R.id.password); // Assuming you have a password EditText with id 'password'
        submitButton = findViewById(R.id.signupBtn);


        userService = ApiClient.getRetrofitInstance().create(UserService.class);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name = nameEt.getText().toString();
//                String email = emailEt.getText().toString();
//                String password = passwordEt.getText().toString();
//
//                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(SignupActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                User newUser = new User(name, email, password);
                processFormFields();
            }
        });
    }

    private void processFormFields() {
        // Request To Server
        RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);

        // URL to post at
        String url = "http://192.168.10.13:8080/api/v2/user/register"; // For emulator

        // String Request Object
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("success")) {
                    nameEt.setText(null);
                    emailEt.setText(null);
                    passwordEt.setText(null);
                    Toast.makeText(SignupActivity.this, "Registration done", Toast.LENGTH_SHORT).show();
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
                params.put("name", nameEt.getText().toString());
                params.put("email", emailEt.getText().toString());
                params.put("password", passwordEt.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }


//    private void createUser(User user) {
//        Call<User> call = userService.registerUser(user);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if (response.isSuccessful()) {
//                    // User created successfully
//                } else {
//                    try {
//                        Log.e("SignupActivity", "Failed to create user: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        Log.e("SignupActivity", "Error reading error body", e);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                try {
//                    if (call.isExecuted()) {
//                        Response<User> executionResult = call.execute();
//                        if (executionResult.errorBody()!= null) {
//                            InputStream inputStream = executionResult.errorBody().byteStream();
//                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                            StringBuilder stringBuilder = new StringBuilder();
//                            String line;
//                            while ((line = bufferedReader.readLine())!= null) {
//                                stringBuilder.append(line);
//                            }
//                            Log.e("SignupActivity", "Error response: " + stringBuilder.toString());
//                        }
//                    }
//                } catch (IOException e) {
//                    Log.e("SignupActivity", "Error reading error body", e);
//                }
//                Toast.makeText(SignupActivity.this, "Network error occurred!", Toast.LENGTH_SHORT).show();
//            }
//
//            // ...
//        });
//    }
}