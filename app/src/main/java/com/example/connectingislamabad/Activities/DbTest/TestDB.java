package com.example.connectingislamabad.Activities.DbTest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.connectingislamabad.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;


public class TestDB extends AppCompatActivity {

    private EditText nameEditText;

    private Button signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);
        System.out.println("Run");

        new DatabaseOperation().execute();

    }

    private class DatabaseOperation extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Connection conn = DatabaseConnection.getConnection();
                // Perform your database operations here
                // For example, execute a query
                 conn.close(); // Re                member to close the connection when done
            } catch (SQLException | java.sql.SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(TestDB.this, "Database operation completed", Toast.LENGTH_SHORT).show();
        }
    }
}

