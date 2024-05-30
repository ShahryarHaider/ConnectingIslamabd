package com.example.connectingislamabad.Activities.Database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    private Context context;

    public DatabaseHelper(Context context) {
        this.context = context;
    }

    @SuppressLint("StaticFieldLeak")
    public void signUp(String name, String email, String password) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                try {
                    Connection connection = connect();
                    if (connection!= null) {
                        String query = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
                        PreparedStatement statement = connection.prepareStatement(query);
                        statement.setString(1, name);
                        statement.setString(2, email);
                        statement.setString(3, password); // Consider hashing the password here
                        int rowsInserted = statement.executeUpdate();
                        connection.close();
                        return rowsInserted > 0;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                super.onPostExecute(success);
                if (success) {
                    Toast.makeText(context, "Sign-up successful!", Toast.LENGTH_SHORT).show();
                    // Proceed to main activity or any other action
                } else {
                    Toast.makeText(context, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    public Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String username = "postgres";
        String password = "123";
        return DriverManager.getConnection(url, username, password);
    }
}
