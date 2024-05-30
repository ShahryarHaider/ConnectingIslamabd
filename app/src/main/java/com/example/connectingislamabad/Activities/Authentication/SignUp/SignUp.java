package com.example.connectingislamabad.Activities.Authentication.SignUp;

import com.example.connectingislamabad.Activities.Database.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {
    private final DatabaseHelper databaseHelper;

    public SignUp(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public void signUp(String name, String email, String password) {
        try (Connection connection = databaseHelper.connect()) {
            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}