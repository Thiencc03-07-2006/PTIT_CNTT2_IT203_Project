package dao;

import model.OrderDetail;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
    private static OrderDetailDAO instance;

    public static OrderDetailDAO getInstance() {
        if (instance == null) {
            instance = new OrderDetailDAO();
        }
        return instance;
    }

    //INSERT (transaction)
    public boolean insert(OrderDetail detail, Connection conn) {
        String sql = """
                INSERT INTO order_details(order_id, product_id, product_name, price, quantity, total_money,color,storage)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detail.getOrderId());
            ps.setInt(2, detail.getProductId());
            ps.setString(3, detail.getProductName());
            ps.setDouble(4, detail.getPrice());
            ps.setInt(5, detail.getQuantity());
            ps.setDouble(6, detail.getTotalMoney());
            ps.setString(7, detail.getColor());
            ps.setString(8, detail.getStorage());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy chi tiết theo order
    public List<OrderDetail> findByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();

        String sql = "SELECT * FROM order_details WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail d = new OrderDetail();

                d.setOrderDetailId(rs.getInt("order_detail_id"));
                d.setOrderId(rs.getInt("order_id"));
                d.setProductId(rs.getInt("product_id"));
                d.setProductName(rs.getString("product_name"));
                d.setPrice(rs.getDouble("price"));
                d.setQuantity(rs.getInt("quantity"));
                d.setTotalMoney(rs.getDouble("total_money"));
                d.setColor(rs.getString("color"));
                d.setStorage(rs.getString("storage"));

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}