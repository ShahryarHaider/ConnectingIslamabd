package com.example.connectingislamabad.Activities.DbTest;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "DatabaseTask";

    private static final String URL = "jdbc:postgresql://localhost:5432/connecting";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Class.forName("org.postgresql.Driver");
            Log.d(TAG, "Driver loaded");

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Log.d(TAG, "Connected to database");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT VERSION()");

            if (resultSet.next()) {
                String version = resultSet.getString(1);
                Log.d(TAG, "Database version: " + version);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            Log.e(TAG, "Driver not found", e);
        } catch (SQLException e) {
            Log.e(TAG, "SQL Exception", e);
        }
        return null;
    }
}

