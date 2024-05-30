package com.example.connectingislamabad.Activities.DbTest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connectingislamabad.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB extends AppCompatActivity {

    private EditText editTextHostname, editTextPort, editTextDatabaseName, editTextUsername, editTextPassword;
    private Button buttonTestConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        editTextHostname = findViewById(R.id.editTextHostname);
        // Initialize other EditText fields
        editTextPort = findViewById(R.id.editTextPort);
        editTextDatabaseName = findViewById(R.id.editTextDatabaseName);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);

        buttonTestConnection = findViewById(R.id.buttonTestConnection);
        buttonTestConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testDatabaseConnection();
            }
        });
    }

    private void testDatabaseConnection() {
        String hostname = editTextHostname.getText().toString();
        String port = editTextPort.getText().toString();
        String databaseName = editTextDatabaseName.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + databaseName;

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            showMessage("Connected to PostgreSQL database!");
            connection.close();
        } catch (SQLException e) {
            showMessage("Failed to connect to PostgreSQL database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
