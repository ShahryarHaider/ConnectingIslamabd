package com.example.connectingislamabad.Activities.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/connecting";
    private static final String USER = "postgres";
    private static final String PASS = "root";

    public void signUp(String name, String email, String password) {

        try (Connection connection = connect()) {

            String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
                System.out.println("Sucuessfully Created DB \n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ;
    }

    public boolean signIn(String email, String password) {
        try (Connection connection = connect()) {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection connect() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver not found", e);
        }
        return connection;
    }
}
