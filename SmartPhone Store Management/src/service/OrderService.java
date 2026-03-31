package service;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderDetail;
import model.OrderStatus;
import util.DBConnection;
import util.Session;

import java.sql.Connection;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();
    private final ProductDAO productDAO = ProductDAO.getInstance();

    public static OrderService getInstance() {
        if (instance == null) instance = new OrderService();
        return instance;
    }

    public List<Order> getOrders() {
        return orderDAO.findAll();
    }

    public List<Order> getMyOrders(int customerId) {
        return orderDAO.findByCustomer(customerId);
    }

    public List<OrderDetail> getOrderDetails(int orderId) {
        return orderDetailDAO.findByOrderId(orderId);
    }

    public Order findOrderById(int order_id) {
        return orderDAO.findByIdAndCustomer(order_id, Session.getCurrentUser().getUserId());
    }

    public boolean updateStatus(int orderId) {
        Order order = orderDAO.findById(orderId);

        if (order == null) {
            System.err.println("Order not found!");
            return false;
        }

        OrderStatus current = order.getStatus();
        OrderStatus next;

        switch (current) {
            case PENDING -> next = OrderStatus.SHIPPING;
            case SHIPPING -> next = OrderStatus.DELIVERED;
            case DELIVERED -> {
                System.err.println("Order already delivered!");
                return false;
            }
            case CANCELLED -> {
                System.err.println("Order cancelled!");
                return false;
            }
            default -> {
                return false;
            }
        }

        return orderDAO.updateStatus(orderId, next.name());
    }

    public boolean cancelOrder(int orderId) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. lấy order
            Order order = orderDAO.findById(orderId, conn);

            if (order == null) {
                System.err.println("Order not found!");
                return false;
            }

            if (order.getStatus() != OrderStatus.PENDING) {
                System.err.println("Only PENDING orders can be cancelled!");
                return false;
            }

            // 2. lấy order detail
            List<OrderDetail> details = orderDetailDAO.findByOrderId(orderId, conn);

            // 3. hoàn lại kho
            for (OrderDetail d : details) {
                productDAO.updateInventory(d.getProductId(), d.getQuantity(), conn);
            }

            // 4. update trạng thái
            boolean result = orderDAO.cancelOrder(orderId, conn);

            if (!result) throw new RuntimeException("Cancel failed");

            conn.commit();
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
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
