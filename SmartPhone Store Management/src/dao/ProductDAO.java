package dao;

import model.Product;
import model.ProductStatus;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static ProductDAO instance;

    public static ProductDAO getInstance() {
        if (instance == null) instance = new ProductDAO();
        return instance;
    }

    // ================= CREATE =================
    public boolean save(Product p) {
        String sql = "INSERT INTO products (product_name, root_price, discount, inventory, color, description, cate_id, brand, storage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getRootPrice());
            ps.setInt(3, p.getDiscount());
            ps.setInt(4, p.getInventory());
            ps.setString(5, p.getColor());
            ps.setString(6, p.getDescription());
            ps.setInt(7, p.getCateId());
            ps.setString(8, p.getBrand());
            ps.setString(9, p.getStorage());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= READ ALL =================
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapToProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findAllActive() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE status <> 'INACTIVE'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapToProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= FIND BY ID =================
    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return mapToProduct(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product findById(int id, Connection conn) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return mapToProduct(rs);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================= UPDATE =================
    public boolean update(Product p) {
        String sql = "UPDATE products SET product_name=?, root_price=?, discount=?, inventory=?, color=?, description=?, cate_id=?, brand=?, storage=?, status=? WHERE product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getProductName());
            ps.setDouble(2, p.getRootPrice());
            ps.setInt(3, p.getDiscount());
            ps.setInt(4, p.getInventory());
            ps.setString(5, p.getColor());
            ps.setString(6, p.getDescription());
            ps.setInt(7, p.getCateId());
            ps.setString(8, p.getBrand());
            ps.setString(9, p.getStorage());
            ps.setString(10, p.getStatus().name());
            ps.setInt(11, p.getProductId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= DELETE =================
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean softDelete(int id) {
        String sql = "UPDATE products SET status = 'INACTIVE' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= SEARCH =================
    public List<Product> search(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_name LIKE ? OR brand LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapToProduct(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= MAP =================
    private Product mapToProduct(ResultSet rs) throws SQLException {
        Product p = new Product();

        p.setProductId(rs.getInt("product_id"));
        p.setProductName(rs.getString("product_name"));
        p.setRootPrice(rs.getDouble("root_price"));
        p.setDiscount(rs.getInt("discount"));
        p.setInventory(rs.getInt("inventory"));
        p.setColor(rs.getString("color"));
        p.setDescription(rs.getString("description"));
        p.setCateId(rs.getInt("cate_id"));
        p.setBrand(rs.getString("brand"));
        p.setStorage(rs.getString("storage"));
        p.setStatus(ProductStatus.valueOf(rs.getString("status")));
        p.setCreatedDate(rs.getTimestamp("created_date"));

        return p;
    }

    public boolean updateInventory(int productId, int delta, Connection conn) {
        String sql = "UPDATE products SET inventory = inventory + ? WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, delta);      // có thể âm (trừ kho)
            ps.setInt(2, productId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}