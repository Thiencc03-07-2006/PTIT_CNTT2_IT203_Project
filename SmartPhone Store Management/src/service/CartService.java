package service;

import dao.*;
import model.*;
import util.DBConnection;
import util.Session;

import java.sql.Connection;
import java.util.List;

public class CartService {
    private static CartService instance;
    private CartDAO cartDAO = CartDAO.getInstance();

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public boolean addToCart(int customerId, Product product, int quantity) {

        if (quantity > product.getInventory()) {
            System.err.println("Quantity exceeds inventory!");
            return false;
        }

        CartItem existing = cartDAO.findByCustomerAndProduct(customerId, product.getProductId());

        if (existing != null) {
            int newQuantity = existing.getQuantity() + quantity;

            if (newQuantity > product.getInventory()) {
                System.err.println("Total quantity exceeds inventory!");
                return false;
            }

            return cartDAO.updateQuantity(customerId, existing.getCartId(), newQuantity);

        } else {
            CartItem cart = new CartItem();
            cart.setCustomerId(customerId);
            cart.setProductId(product.getProductId());
            cart.setQuantity(quantity);

            return cartDAO.insert(cart);
        }
    }

    public List<ProductCart> getCartByCustomer(int customerId) {
        return cartDAO.getCartByCustomer(customerId);
    }

    public boolean updateQuantity(int customerId, int cartId, int quantity) {
        if (quantity <= 0) {
            System.err.println("Quantity must > 0 !");
            return false;
        }

        CartItem cartItem = cartDAO.findById(cartId);
        if (cartItem == null) {
            System.err.println("Cart not found!");
            return false;
        }

        Product product = ProductService.getInstance()
                .findById(cartItem.getProductId());

        if (product == null) {
            System.err.println("Product not found!");
            return false;
        }

        if (quantity > product.getInventory()) {
            System.err.println("Quantity exceeds inventory!");
            return false;
        }
        return cartDAO.updateQuantity(customerId, cartId, quantity);
    }

    public boolean deleteItem(int customerId, int cartId) {
        return cartDAO.deleteItem(customerId, cartId);
    }

    public boolean clearCart(int customerId) {
        return cartDAO.clearCart(customerId);
    }

    public boolean checkout(int customerId) {
        Connection conn = null;
        ProductDAO productDAO=ProductDAO.getInstance();
        OrderDAO orderDAO=OrderDAO.getInstance();
        OrderDetailDAO orderDetailDAO=OrderDetailDAO.getInstance();
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            List<ProductCart> carts = cartDAO.getCartByCustomer(customerId, conn);

            if (carts.isEmpty()) {
                System.err.println("Cart empty!");
                return false;
            }

            // CHECK INVENTORY
            for (ProductCart item : carts) {
                Product product = productDAO.findById(item.getProductId(), conn);

                if (item.getQuantity() > product.getInventory()) {
                    System.err.println("Product out of stock: " + product.getProductName());
                    conn.rollback();
                    return false;
                }
            }

            // ORDER
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setCustomerName(Session.getCurrentUser().getUserName());
            order.setPhoneNumber(Session.getCurrentUser().getPhoneNumber());
            order.setAddress(Session.getCurrentUser().getAddress());
            order.setEmail(Session.getCurrentUser().getEmail());

            double totalMoney = carts.stream()
                    .mapToDouble(ProductCart::getTotalMoney)
                    .sum();

            order.setTotalMoney(totalMoney);

            int orderId = orderDAO.insert(order, conn);

            if (orderId <= 0) {
                conn.rollback();
                return false;
            }

            // ORDER DETAIL + UPDATE STOCK
            for (ProductCart item : carts) {

                OrderDetail detail = new OrderDetail();
                detail.setOrderId(orderId);
                detail.setProductId(item.getProductId());
                detail.setProductName(item.getProductName());
                detail.setPrice(item.getPrice());
                detail.setQuantity(item.getQuantity());
                detail.setTotalMoney(item.getTotalMoney());
                detail.setColor(item.getColor());
                detail.setStorage(item.getStorage());

                boolean rsDetail = orderDetailDAO.insert(detail, conn);
                if (!rsDetail) {
                    conn.rollback();
                    return false;
                }

                boolean rsStock = productDAO.updateInventory(
                        item.getProductId(),
                        -item.getQuantity(),
                        conn
                );

                if (!rsStock) {
                    conn.rollback();
                    return false;
                }
            }

            boolean clear = cartDAO.clearCart(customerId, conn);
            if (!clear) {
                conn.rollback();
                return false;
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
