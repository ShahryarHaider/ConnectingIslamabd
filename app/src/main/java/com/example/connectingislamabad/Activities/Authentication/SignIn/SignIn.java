//package com.example.connectingislamabad.Activities.Authentication.SignIn;
//
//import com.example.connectingislamabad.Activities.Database.DatabaseHelper;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class SignIn {
//    private final DatabaseHelper databaseHelper;
//
//    public SignIn(DatabaseHelper databaseHelper) {
//        this.databaseHelper = databaseHelper;
//    }
//
//    public boolean signIn(String email, String password) {
//        DatabaseHelper databaseHelper = new DatabaseHelper();
//        boolean isAuthenticated;
//        try (Connection connection = databaseHelper.connect()) {
//            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
//            isAuthenticated = true;
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, email);
//                preparedStatement.setString(2, password);
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    isAuthenticated = resultSet.next();
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            isAuthenticated = false; // Handle error
//        }
//        return isAuthenticated;
//    }
//
//}