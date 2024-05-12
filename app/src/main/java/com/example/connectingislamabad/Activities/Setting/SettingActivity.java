package com.example.connectingislamabad.Activities.Setting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Transport.TransportActivity;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    private Switch notificationSwitch;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.setting_bottom);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_bottom) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.category_bottom) {
                    // Handle navigation to category
                    // Start the CategoryActivity

                    return true;
                } else if (item.getItemId() == R.id.transport_bottom) {
                    // Handle navigation to transport
                    // Start the TransportActivity
                    startActivity(new Intent(getApplicationContext(), TransportActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.setting_bottom) {
                    // Handle navigation to settings
                    // Start the SettingsActivity
                    startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Initialize views
        notificationSwitch = findViewById(R.id.notification_switch);
        saveButton = findViewById(R.id.save_button);

        // Load saved settings or initialize default settings
        loadSettings();

        // Set listener for the switch
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle notification switch state change
            }
        });

        // Set listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSettings();
            }
        });
    }

    private void loadSettings() {
        // Load saved settings from SharedPreferences or other storage
        // For demonstration purposes, we'll just set default values
        boolean enableNotifications = true; // Example: Default to true
        notificationSwitch.setChecked(enableNotifications);
    }

    private void saveSettings() {
        // Save current settings to SharedPreferences or other storage
        boolean enableNotifications = notificationSwitch.isChecked();

        // For demonstration purposes, we'll just show a toast message
        String message = enableNotifications ? "Notifications enabled" : "Notifications disabled";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
