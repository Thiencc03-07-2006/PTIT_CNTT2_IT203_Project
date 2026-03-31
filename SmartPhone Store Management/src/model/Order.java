package model;

import dao.OrderDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.OrderService;
import util.InputMethod;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer orderId;
    private String customerName;
    private Integer customerId;
    private String phoneNumber;
    private String email;
    private String address;
    private Double totalMoney;
    private OrderStatus status = OrderStatus.PENDING;
    private Date createdDate;

    public boolean updateStatus(int orderId) {
        OrderDAO orderDAO=new OrderDAO();
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

    public String printStatus(OrderStatus status) {
        String rs = "";
        switch (status) {
            case OrderStatus.CANCELLED: {
                rs = "Cancel";
                break;
            }
            case OrderStatus.PENDING: {
                rs = "ordered";
                break;
            }
            case OrderStatus.SHIPPING: {
                rs = "Confirm";
                break;
            }
            case OrderStatus.DELIVERED: {
                rs = "delivering";
                break;
            }
        }
        return rs;
    }

    public void displayData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3d | %-28s | %-13s | %-28s | %-18s | %-13s | %-13s |\n", orderId, customerName, phoneNumber, address, numberFormat.format(totalMoney) + " VND", status, simpleDateFormat.format(createdDate));
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
    }

}