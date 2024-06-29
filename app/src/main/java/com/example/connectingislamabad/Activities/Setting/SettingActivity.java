package com.example.connectingislamabad.Activities.Setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.Activities.Authentication.SignIn.SigninActivity;
import com.example.connectingislamabad.Activities.Category.CategoryActivity;
import com.example.connectingislamabad.Activities.Form.FeedbackActivity;
import com.example.connectingislamabad.Activities.Main.MainActivity;
import com.example.connectingislamabad.Activities.Profile.EditProfileActivity;
import com.example.connectingislamabad.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    private Switch notificationSwitch;
    private Button signoutButton;
    private Button editProfileButton, feedback_button;
    private TextView o_name, o_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        notificationSwitch = findViewById(R.id.notification_switch);
        signoutButton = findViewById(R.id.signout_button);
        editProfileButton = findViewById(R.id.edit_profile_button);
        feedback_button = findViewById(R.id.feedback_button);

        // Set click listeners
        editProfileButton.setOnClickListener(v -> {
            // Start EditProfileActivity
            startActivity(new Intent(SettingActivity.this, EditProfileActivity.class));
        });

        feedback_button.setOnClickListener(v -> {
            // Start EditProfileActivity
            startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
        });


        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        String email = sharedPreferences.getString("email", "user@example.com");


        o_name = findViewById(R.id.name);
        o_email = findViewById(R.id.email);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        String userId = currentUser.getUid();
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String userName = document.getString("name");
                    String userEmail = document.getString("email");

                    if (userEmail != null) {
                        o_email.setText(userEmail);
                    }
                    if (userName != null) {
                        o_name.setText(userName);
                    }
                } else {
                    Log.d("MainActivity", "No such document");
                }
            } else {
                Log.d("MainActivity", "get failed with ", task.getException());
            }
        });

        boolean isNotificationsEnabled = loadNotificationPreference();
        notificationSwitch.setChecked(isNotificationsEnabled);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveNotificationPreference(isChecked);
            Toast.makeText(SettingActivity.this, isChecked ? "Notifications Enabled" : "Notifications Disabled", Toast.LENGTH_SHORT).show();
        });

        signoutButton.setOnClickListener(v -> {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.clear();
//            editor.apply();
            signOut();

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

    private void signOut() {
        // Sign out the user
        mAuth.signOut();
        // Redirect to SignInActivity
        Intent intent = new Intent(SettingActivity.this, SigninActivity.class);
        startActivity(intent);
        finish(); // Prevent the user from returning to this activity
    }

    private boolean loadNotificationPreference() {
        // Placeholder for loading the notification preference
        return true;
    }

    private void saveNotificationPreference(boolean isEnabled) {
        // Placeholder for saving the notification preference
    }
}
