package com.example.connectingislamabad.Activities.DbTest;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = "DatabaseTask";

    @Override
    protected Void doInBackground(Void... voids) {
        Connection connection = null;
        try {
            System.out.println("outSide DB CLass");

            // Load the PostgreSQL JDBC driver class
            Class.forName("org.postgresql.Driver");

            System.out.println("In Side DB CLass");

            // Connect to the PostgreSQL database
            String url = "jdbc:postgresql:localhost:5432/connecting";
            String username = "postgres";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);

            // Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS test_table ("
                    + "id SERIAL PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "age INT"
                    + ")";
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            Log.d(TAG, "Table created successfully.");

        } catch (ClassNotFoundException e) {
            Log.e(TAG, "PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            Log.e(TAG, "Error executing SQL command.");
            e.printStackTrace();
        } finally {
            // Close the connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                Log.e(TAG, "Error closing connection.");
                e.printStackTrace();
            }
        }
        return null;
    }
}

