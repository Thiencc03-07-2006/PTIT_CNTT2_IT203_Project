package dao;

import model.Order;
import model.OrderStatus;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private static OrderDAO instance;

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM orders ORDER BY created_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setPhoneNumber(rs.getString("phone_number"));
                o.setAddress(rs.getString("address"));
                o.setTotalMoney(rs.getDouble("total_money"));
                o.setStatus(Enum.valueOf(model.OrderStatus.class, rs.getString("status")));
                o.setCreatedDate(rs.getTimestamp("created_date"));

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // INSERT dùng transaction
    public int insert(Order order, Connection conn) {
        String sql = """
                INSERT INTO orders(customer_id, customer_name, phone_number,email, address, total_money, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, order.getCustomerId());
            ps.setString(2, order.getCustomerName());
            ps.setString(3, order.getPhoneNumber());
            ps.setString(4, order.getEmail());
            ps.setString(5, order.getAddress());
            ps.setDouble(6, order.getTotalMoney());
            ps.setString(7, order.getStatus().name());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Lấy danh sách order theo customer
    public List<Order> findByCustomer(int customerId) {
        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM orders WHERE customer_id = ? ORDER BY created_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setPhoneNumber(rs.getString("phone_number"));
                o.setAddress(rs.getString("address"));
                o.setTotalMoney(rs.getDouble("total_money"));
                o.setStatus(Enum.valueOf(model.OrderStatus.class, rs.getString("status")));
                o.setCreatedDate(rs.getTimestamp("created_date"));

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Order findByIdAndCustomer(int orderId, int customerId) {
        String sql = "SELECT * FROM orders WHERE order_id = ? AND customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, customerId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setPhoneNumber(rs.getString("phone_number"));
                o.setEmail(rs.getString("email"));
                o.setAddress(rs.getString("address"));
                o.setTotalMoney(rs.getDouble("total_money"));
                o.setStatus(OrderStatus.valueOf(rs.getString("status")));
                o.setCreatedDate(rs.getTimestamp("created_date"));
                return o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateStatus(int orderId, String status) {
        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, orderId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Order findById(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setPhoneNumber(rs.getString("phone_number"));
                o.setEmail(rs.getString("email"));
                o.setAddress(rs.getString("address"));
                o.setTotalMoney(rs.getDouble("total_money"));
                o.setStatus(OrderStatus.valueOf(rs.getString("status")));
                o.setCreatedDate(rs.getTimestamp("created_date"));
                return o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Order findById(int orderId,Connection conn) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt("order_id"));
                o.setCustomerId(rs.getInt("customer_id"));
                o.setCustomerName(rs.getString("customer_name"));
                o.setPhoneNumber(rs.getString("phone_number"));
                o.setEmail(rs.getString("email"));
                o.setAddress(rs.getString("address"));
                o.setTotalMoney(rs.getDouble("total_money"));
                o.setStatus(OrderStatus.valueOf(rs.getString("status")));
                o.setCreatedDate(rs.getTimestamp("created_date"));
                return o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean cancelOrder(int orderId, Connection conn) throws SQLException {
        String sql = "UPDATE orders SET status='CANCELLED' WHERE order_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            return ps.executeUpdate() > 0;
        }
    }
}