package com.example.connectingislamabad.Activities.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.connectingislamabad.Activities.Setting.SettingActivity;
import com.example.connectingislamabad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;

    private EditText newNameEt, newEmailEt, newPasswordEt;
    private Button updateButton, changePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        newNameEt = findViewById(R.id.newName);
        updateButton = findViewById(R.id.updateBtn);
        changePass = findViewById(R.id.changePass);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String userName = document.getString("name");

                    if (userName != null) {
                        newNameEt.setText(userName);
                    }
                } else {
                    Log.d("MainActivity", "No such document");
                }
            } else {
                Log.d("MainActivity", "get failed with ", task.getException());
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newNameText = newNameEt.getText().toString();
                if (validateForm()) {
                    updateUserName(userId, newNameText);
                }
            }
        });

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }

    private void updateUserName(String userId, String newName) {
        // Reference to the user's document in Firestore
        DocumentReference userDocRef = db.collection("users").document(userId);

        // Update the name field
        userDocRef.update("name", newName)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "User name updated successfully.");
                        Toast.makeText(EditProfileActivity.this, "Name updated successfully!", Toast.LENGTH_SHORT).show();
                        newNameEt.setText(newName);
                        Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating user name", e);
                        Toast.makeText(EditProfileActivity.this, "Failed to update name: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }




    // Form validation
    private boolean validateForm() {
        String name = newNameEt.getText().toString().trim();

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