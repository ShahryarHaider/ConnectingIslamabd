package com.example.connectingislamabad.Activities.Setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.Activities.Authentication.SignIn.SigninActivity;
import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Profile.EditProfileActivity;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingActivity extends AppCompatActivity {

    private Switch notificationSwitch;
    private Button signoutButton;
    private Button editProfileButton;
    private TextView o_name, o_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        notificationSwitch = findViewById(R.id.notification_switch);
        signoutButton = findViewById(R.id.signout_button);
        editProfileButton = findViewById(R.id.edit_profile_button);

        // Set click listeners
        editProfileButton.setOnClickListener(v -> {
            // Start EditProfileActivity
            startActivity(new Intent(SettingActivity.this, EditProfileActivity.class));
        });

        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        String email = sharedPreferences.getString("email", "user@example.com");

        o_name = findViewById(R.id.name);
        o_email = findViewById(R.id.email);

        o_name.setText(name);
        o_email.setText(email);

        boolean isNotificationsEnabled = loadNotificationPreference();
        notificationSwitch.setChecked(isNotificationsEnabled);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveNotificationPreference(isChecked);
            Toast.makeText(SettingActivity.this, isChecked ? "Notifications Enabled" : "Notifications Disabled", Toast.LENGTH_SHORT).show();
        });

        signoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Toast.makeText(SettingActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SettingActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        });



        // Navigation Bar Controller
        BottomNavigationView bottomNavigationView = findViewById(R.id.buttomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.setting_bottom);

        // Navigation bottom Bar
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
                    startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.transport_bottom) {
                    // Handle navigation to transport
                    // Start the TransportActivity

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
    }

    private boolean loadNotificationPreference() {
        // Placeholder for loading the notification preference
        return true;
    }

    private void saveNotificationPreference(boolean isEnabled) {
        // Placeholder for saving the notification preference
    }
}
