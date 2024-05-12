package com.example.connectingislamabad.Activities.Authentication.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.Activities.Database.DatabaseHelper;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;

public class SignupActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEditText = findViewById(R.id.nameForm);
        emailEditText = findViewById(R.id.emailForm);
        passwordEditText = findViewById(R.id.passwordForm);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }


    private void signUp() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        int password = Integer.parseInt(passwordEditText.getText().toString());

        DatabaseHelper databaseHelper = new DatabaseHelper();
        boolean success = databaseHelper.signUp(name, email, String.valueOf(password));

        if (success) {
            Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}