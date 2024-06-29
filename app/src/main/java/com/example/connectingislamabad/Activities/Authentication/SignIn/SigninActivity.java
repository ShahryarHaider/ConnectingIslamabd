package com.example.connectingislamabad.Activities.Authentication.SignIn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.connectingislamabad.Activities.Authentication.SignUp.SignupActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SigninActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button signinButton;
    private TextView skip, signUpClick, forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailForm);
        passwordEditText = findViewById(R.id.passwordForm);
        signinButton = findViewById(R.id.signinButton);
        skip = findViewById(R.id.skip);
        signUpClick = findViewById(R.id.signUpClick);
        forgetPass = findViewById(R.id.forgetPass);

        signUpClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();
                    signIn(email, password);
                    //login();
                }
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivityIntent = new Intent(SigninActivity.this, MainActivity.class);
                mainActivityIntent.putExtra("val", "skip");
                startActivity(mainActivityIntent);
                finish();
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                if(email.isEmpty())
                {
                    Toast.makeText(SigninActivity.this, "Please Enter your Email", Toast.LENGTH_LONG).show();
                }
                else
                {
                    resetPassword(email);
                }
            }
        });


    }

    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast.makeText(SigninActivity.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                            // Redirect to main activity or any other activity
                            Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SignInActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

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

    private void resetPassword(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SigninActivity.this, "Reset link sent to your email", Toast.LENGTH_LONG).show();
                            Log.d("ForgotPassword", "Email sent.");
                        } else {
                            Toast.makeText(SigninActivity.this, "Unable to send reset mail", Toast.LENGTH_LONG).show();
                            Log.d("ForgotPassword", "Failed to send email.");
                        }
                    }
                });
    }

//    private void login() {
//        RequestQueue queue = Volley.newRequestQueue(SigninActivity.this);
//
//        String url = "http://192.168.10.31:8080/api/v2/user/login";
//
//        HashMap<String, String> params = new HashMap<>();
//        params.put("email", emailEditText.getText().toString());
//        params.put("password", passwordEditText.getText().toString());
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
////                            String token = response.getString("token");
////                            String userId = response.getString("id"); // Adjust according to your actual JSON response
//                            String name = response.getString("name");
//                            String email = response.getString("email");
//
//                            SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putBoolean("is_logged_in", true);
////                            editor.putString("jwt_token", token);
////                            editor.putString("user_id", userId);
//                            editor.putString("name", name);
//                            editor.putString("email", email);
//                            editor.apply();
//
//                            Intent mainActivity = new Intent(SigninActivity.this, MainActivity.class);
//                            mainActivity.putExtra("name", name);
//                            mainActivity.putExtra("email", email);
//                            startActivity(mainActivity);
//                            finish();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(SigninActivity.this, "Unsuccessful: " + error.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        queue.add(jsonObjectRequest);
//    }
}
