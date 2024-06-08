package com.example.connectingislamabad.Activities.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.R;

public class EditProfileActivity extends AppCompatActivity {

    private EditText newNameEt, newEmailEt;
    private Button updateButton;

    private String currentName, currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        newNameEt = findViewById(R.id.new_name);
        newEmailEt = findViewById(R.id.new_email);
        updateButton = findViewById(R.id.update_button);

        // Retrieve current user's name and email from extras
        Intent intent = getIntent();
        currentName = intent.getStringExtra("name");
        currentEmail = intent.getStringExtra("email");

        // Populate EditText fields with current user's name and email
        newNameEt.setText(currentName);
        newEmailEt.setText(currentEmail);

        // Update profile button click listener
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated name and email from EditText fields
                String newName = newNameEt.getText().toString().trim();
                String newEmail = newEmailEt.getText().toString().trim();

                // Perform validation if needed

                // Update profile logic (replace with your actual logic)
                // For demonstration purposes, simply display a toast message with the updated information
                String message = "Updated Profile\nName: " + newName + "\nEmail: " + newEmail;
                Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_SHORT).show();

                // Optionally, you can send the updated profile information back to the previous activity
                // and update the UI accordingly
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newName", newName);
                resultIntent.putExtra("newEmail", newEmail);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
