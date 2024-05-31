package com.example.connectingislamabad.Activities.Setting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.Activities.Authentication.SignIn.SigninActivity;
import com.example.connectingislamabad.R;

public class SettingActivity extends AppCompatActivity {

    private Switch notificationSwitch;
    private Button signoutButton;
    private Button editProfileButton;

    private TextView o_name , o_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Intialize
        notificationSwitch = findViewById(R.id.notification_switch);
        signoutButton = findViewById(R.id.signout_button);
        editProfileButton = findViewById(R.id.edit_profile_button);

        // Retrieve passed data
//        Intent intent = getIntent();
//        String names = intent.getStringExtra("name");
//        String emails = intent.getStringExtra("email");

//        // Display user information
//        if (names != null && emails != null) {
//            o_name.setText("Name: " + names);
//            o_email.setText("Email: " + emails);
//        }

        // Load initial switch state (this is a placeholder, replace with actual logic)
        boolean isNotificationsEnabled = loadNotificationPreference();
        notificationSwitch.setChecked(isNotificationsEnabled);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Save the new preference (this is a placeholder, replace with actual logic)
            saveNotificationPreference(isChecked);
            Toast.makeText(SettingActivity.this, isChecked ? "Notifications Enabled" : "Notifications Disabled", Toast.LENGTH_SHORT).show();
        });

        signoutButton.setOnClickListener(v -> {
            // Handle sign out logic here
            Toast.makeText(SettingActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
            // Optionally, redirect to a login activity or clear user session
            Intent intent = new Intent(SettingActivity.this, SigninActivity.class);
            startActivity(intent);
            finish(); // Close the current activity
        });

        editProfileButton.setOnClickListener(v -> {
            // Handle edit profile logic here
            Toast.makeText(SettingActivity.this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show();
            // Optionally, redirect to an edit profile activity
        });
    }

    private boolean loadNotificationPreference() {
        // Placeholder for loading the notification preference
        // Replace with actual logic to load from SharedPreferences or other storage
        return true;
    }

    private void saveNotificationPreference(boolean isEnabled) {
        // Placeholder for saving the notification preference
        // Replace with actual logic to save to SharedPreferences or other storage
    }
}
