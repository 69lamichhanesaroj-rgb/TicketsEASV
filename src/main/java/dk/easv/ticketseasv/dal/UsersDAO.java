package dk.easv.ticketseasv.dal;

import dk.easv.ticketseasv.be.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    ConnectionManager conMan = new ConnectionManager();

    public User getUser(String login) {
        try (Connection con = conMan.getConnection()) {
            String sql = "SELECT * FROM Users WHERE Login = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                String role = rs.getString("Role");
                String username = rs.getString("Username");
                //No point asking for login since we already have it
                String password = rs.getString("Password");
                String salt = rs.getString("Salt");
                return new User(id, role, username, login, password, salt);
            } else {
                return null; // No user found with the given login
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        try (Connection con = conMan.getConnection()) {
            String sql = "INSERT INTO Users (Role, Username, Login, Password, Salt) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getRole());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getSalt());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection con = conMan.getConnection()) {
            String sql = "SELECT * FROM Users";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String role = rs.getString("Role");
                String username = rs.getString("Username");
                String login = rs.getString("Login");
                String password = rs.getString("Password");
                String salt = rs.getString("Salt");

                users.add(new User(id, role, username, login, password, salt));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }
}
