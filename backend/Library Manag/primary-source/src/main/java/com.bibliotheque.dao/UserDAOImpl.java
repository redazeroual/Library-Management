package com.bibliotheque.dao;

import com.bibliotheque.model.User;
import com.bibliotheque.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {


    public UserDAOImpl() {
        String sql = "INSERT IGNORE INTO userss (username, password, full_name) VALUES ('admin', 'admin123', 'Administrateur')";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public User authenticate(String username, String password) {

        String query = "SELECT id, username, full_name FROM userss WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            System.out.println("Tentative de connexion avec : " + username + " / " + password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("full_name")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        String query = "SELECT COUNT(*) FROM userss WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO userss (username, password, full_name) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFullName());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, username, full_name FROM userss";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("full_name")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return users;
    }


    @Override public boolean updateUser(User user) { return false; }
    @Override public boolean deleteUser(int id) { return false; }
    @Override public User getUserById(int id) { return null; }
}