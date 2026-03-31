package dao;

import model.Gender;
import model.Role;
import model.User;
import util.DBConnection;

import java.sql.*;

public class UserDAO {
    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public boolean save(User user) {
        String sql = "INSERT INTO users(user_name,password,phone_number,email,address) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getAddress());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE user_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                String genderStr = rs.getString("gender");
                if (genderStr != null) {
                    user.setGender(Gender.valueOf(genderStr));
                }
                user.setBirthday(rs.getDate("birthday"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                user.setStatus(rs.getBoolean("status"));
                user.setRole(Role.valueOf(rs.getString("role")));
                user.setCreatedDate(rs.getTimestamp("created_date"));

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsByPhone(String phone) {
        String sql = "SELECT COUNT(*) FROM users WHERE phone = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}