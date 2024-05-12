package com.example.connectingislamabad.Activities.Authentication.SignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.connectingislamabad.Activities.Database.DatabaseHelper;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailEditText = findViewById(R.id.emailForm);
        passwordEditText = findViewById(R.id.passwordForm);
    }

    public void signIn(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        DatabaseHelper databaseHelper = new DatabaseHelper();
        SignIn signIn = new SignIn(databaseHelper);
        boolean isAuthenticated = signIn.signIn(email, password);

        if (isAuthenticated) {
            Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "Sign-in failed. Please check your email and password.", Toast.LENGTH_SHORT).show();
        }
    }
}