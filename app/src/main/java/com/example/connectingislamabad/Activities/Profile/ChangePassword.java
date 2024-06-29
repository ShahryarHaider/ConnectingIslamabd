package com.example.connectingislamabad.Activities.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    private EditText currentPasswordEditText, newPasswordEditText;
    private Button changePasswordButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPasswordEditText = findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        auth = FirebaseAuth.getInstance();

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPassword = currentPasswordEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();

                if (currentPassword.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Enter current password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPassword.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Enter new password", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    // Reauthenticate user first
                    user.reauthenticate(com.google.firebase.auth.EmailAuthProvider.getCredential(user.getEmail(), currentPassword))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Password successfully reauthenticated
                                        // Now change the password
                                        user.updatePassword(newPassword)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(ChangePassword.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                                                            Log.d("ChangePassword", "Password updated");
                                                            finish();
                                                        } else {
                                                            Toast.makeText(ChangePassword.this, "Failed to change password", Toast.LENGTH_SHORT).show();
                                                            Log.d("ChangePassword", "Error updating password", task.getException());
                                                        }
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(ChangePassword.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                        Log.d("ChangePassword", "Error reauthenticating", task.getException());
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ChangePassword.this, "User not authenticated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
