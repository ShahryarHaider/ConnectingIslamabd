package com.example.connectingislamabad;

import android.content.Intent;
import android.os.Bundle;

import com.example.connectingislamabad.Activities.Authentication.SignUp.SignupActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.connectingislamabad.databinding.ActivityTestBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class test extends AppCompatActivity {


    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button btn = findViewById(R.id.btn);

        db = FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> data = new HashMap<>();
                data.put("titleTxt", "");
                data.put("descTxt", "");
                data.put("collection_name", "");
                data.put("document_name", "");


                Map<String, Object> user = new HashMap<>();
                user.put("redline", data);

                // Add a new document with a generated ID
                db.collection("transport").document("1Data")
                        .update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(test.this, "Sign up successful!", Toast.LENGTH_SHORT).show();

                                } else {
                                    Log.w("SignUpActivity", "Error adding document", task.getException());
                                    Toast.makeText(test.this, "Failed to store user info: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}