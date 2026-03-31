package service;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import model.Order;
import model.OrderDetail;
import util.Session;

import java.util.List;

public class OrderService {
    private static OrderService instance;
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final OrderDetailDAO orderDetailDAO = OrderDetailDAO.getInstance();

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
}
