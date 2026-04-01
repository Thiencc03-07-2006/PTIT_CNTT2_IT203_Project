package dao;

import model.CartItem;
import model.ProductCart;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * CartDAO - Lớp quản lý các thao tác dữ liệu liên quan đến Giỏ hàng (Cart).
 * Sử dụng Design Pattern: Singleton để tối ưu hóa việc khởi tạo đối tượng.
 */
public class CartDAO {
    private static CartDAO instance;
//Lấy instance duy nhất của lớp CartDAO.
    public static CartDAO getInstance() {
        if (instance == null) {
            instance = new CartDAO();
        }
        return instance;
    }
//    Tìm kiếm một sản phẩm cụ thể trong giỏ hàng của một khách hàng.
    public CartItem findByCustomerAndProduct(int customerId, int productId) {
        String sql = "SELECT * FROM cart WHERE customer_id=? AND product_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setInt(2, productId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CartItem c = new CartItem();
                c.setCartId(rs.getInt("cart_id"));
                c.setCustomerId(rs.getInt("customer_id"));
                c.setProductId(rs.getInt("product_id"));
                c.setQuantity(rs.getInt("quantity"));
                c.setCreatedDate(rs.getTimestamp("created_date"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//Thêm mới một sản phẩm vào giỏ hàng.
    public boolean insert(CartItem c) {
        String sql = "INSERT INTO cart(customer_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getCustomerId());
            ps.setInt(2, c.getProductId());
            ps.setInt(3, c.getQuantity());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//Cập nhật số lượng của một mục trong giỏ hàng.
    public boolean updateQuantity(int customerId, int cartId, int quantity) {
        String sql = "UPDATE cart SET quantity=? WHERE cart_id=? AND customer_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setInt(3, customerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//Xóa một mục cụ thể khỏi giỏ hàng.
    public boolean deleteItem(int customerId, int cartId) {
        String sql = "DELETE FROM cart WHERE cart_id=? AND customer_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);
            ps.setInt(2, customerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//Xóa sạch giỏ hàng của một khách hàng
    public boolean clearCart(int customerId) {
        String sql = "DELETE FROM cart WHERE customer_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//Xóa sạch giỏ hàng sử dụng Connection được truyền vào
    public boolean clearCart(int customerId, Connection conn) {
        String sql = "DELETE FROM cart WHERE customer_id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//    Lấy danh sách chi tiết các sản phẩm trong giỏ hàng kèm thông tin từ bảng Products
    public List<ProductCart> getCartByCustomer(int customerId) {
        List<ProductCart> list = new ArrayList<>();
        String sql = """
                    SELECT c.cart_id, c.quantity,
                           p.product_id, p.product_name, 
                           p.color, p.storage, 
                           p.root_price, p.discount
                    FROM cart c
                    JOIN products p ON c.product_id = p.product_id
                    WHERE c.customer_id = ?
                """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductCart pc = new ProductCart();

                pc.setCartId(rs.getInt("cart_id"));
                pc.setProductId(rs.getInt("product_id"));
                pc.setProductName(rs.getString("product_name"));
                pc.setColor(rs.getString("color"));
                pc.setStorage(rs.getString("storage"));
                pc.setQuantity(rs.getInt("quantity"));

                double rootPrice = rs.getDouble("root_price");
                int discount = rs.getInt("discount");
                double finalPrice = rootPrice - (rootPrice * discount / 100);
                pc.setPrice(finalPrice);

                list.add(pc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
//    Hàm hỗ trợ lấy giỏ hàng
    public List<ProductCart> getCartByCustomer(int customerId, Connection conn) {
        List<ProductCart> list = new ArrayList<>();
        String sql = """
                    SELECT c.cart_id, c.quantity,
                           p.product_id, p.product_name, 
                           p.color, p.storage, 
                           p.root_price, p.discount
                    FROM cart c
                    JOIN products p ON c.product_id = p.product_id
                    WHERE c.customer_id = ?
                """;
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductCart pc = new ProductCart();

                pc.setCartId(rs.getInt("cart_id"));
                pc.setProductId(rs.getInt("product_id"));
                pc.setProductName(rs.getString("product_name"));
                pc.setColor(rs.getString("color"));
                pc.setStorage(rs.getString("storage"));
                pc.setQuantity(rs.getInt("quantity"));

                double rootPrice = rs.getDouble("root_price");
                int discount = rs.getInt("discount");
                double finalPrice = rootPrice - (rootPrice * discount / 100);
                pc.setPrice(finalPrice);

                list.add(pc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
//Ánh xạ kết quả từ ResultSet sang đối tượng CartItem
    private CartItem mapToCartItem(ResultSet rs) throws SQLException {
        CartItem c = new CartItem();

        c.setCartId(rs.getInt("cart_id"));
        c.setCustomerId(rs.getInt("customer_id"));
        c.setProductId(rs.getInt("product_id"));
        c.setQuantity(rs.getInt("quantity"));
        c.setCreatedDate(rs.getTimestamp("created_date"));

        return c;
    }

    public CartItem findById(int cartId) {
        String sql = "SELECT * FROM cart WHERE cart_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapToCartItem(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
