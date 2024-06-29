package com.example.connectingislamabad.Activities.Form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connectingislamabad.Activities.Authentication.SignUp.SignupActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FeedbackActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private EditText nameED, emailED, phoneED, mesED;

    private Button btn;


    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        nameED = findViewById(R.id.nameField);
        emailED = findViewById(R.id.emailField);
        phoneED = findViewById(R.id.phoneField);
        mesED = findViewById(R.id.messageField);
        btn = findViewById(R.id.submitBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameED.getText().toString();
                String email = emailED.getText().toString();
                String phone = phoneED.getText().toString();
                String mes = mesED.getText().toString();

                storeUserInfo(name, email, phone, mes);
            }
        });

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        userId = currentUser.getUid();

    }

    private void storeUserInfo(String userName, String email, String phone, String message) {
        // Create a new user with name and email
        Map<String, Object> user = new HashMap<>();
        user.put("name", userName);
        user.put("email", email);
        user.put("phone", phone);
        user.put("message", message);

        // Add a new document with a generated ID
        db.collection("feedback").document(userId)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(FeedbackActivity.this, "Submitted successful!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//                            startActivity(intent);
                        } else {
                            Log.w("SignUpActivity", "Error adding document", task.getException());
                            Toast.makeText(FeedbackActivity.this, "Failed to submit your Feedback " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}