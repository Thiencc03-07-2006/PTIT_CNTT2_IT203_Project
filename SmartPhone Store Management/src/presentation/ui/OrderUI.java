package presentation.ui;

import model.Order;
import service.OrderService;
import util.GetColor;
import util.InputMethod;

import java.util.List;

public class OrderUI {
    private final ProductCartUI productCartFeature = new ProductCartUI();
    private final OrderService orderService = new OrderService();

    public void displayList() {
        List<Order> orders = orderService.getOrders();
        showList(orders);
    }

    private static void showList(List<Order> orders) {
        int currentPage = 1;
        int itemPerPage = 5;
        int totalPage = (int) Math.ceil((double) orders.size() / itemPerPage);
        int skip = (currentPage - 1) * itemPerPage;
        int size = orders.size();
        if (orders.isEmpty()) {
            System.err.println("List orders is empty !");
        } else {
            while (true) {
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|                                                              " + GetColor.GREEN + "LIST ORDERS" + GetColor.RESET + "                                                               |");
                System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
                System.out.printf("| %-3s | %-28s | %-13s | %-28s | %-18s | %-13s | %-13s |\n", "ID", "Customer Name", "Phone Number", "Address", "Total Money", "Status", "Created Date");
                System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");

                for (int i = skip; i < (skip + itemPerPage); i++) {
                    if (i < size) {
                        orders.get(i).displayData();
                    } else {
                        break;
                    }
                }
                StringBuilder pagination = new StringBuilder();
                int startPage = Math.max(currentPage - 2, 1);
                int endPage = Math.min(currentPage + 2, totalPage);
                for (int i = startPage; i <= endPage; i++) {
                    if (currentPage == i) {
                        pagination.append(GetColor.RED + "[").append(i).append("]").append(GetColor.RESET);
                    } else {
                        pagination.append("[").append(i).append("]");
                    }

                    pagination.append("     ");
                }
                String rs = "|";
                int spaceStart = (145 - (pagination.length())) / 2;
                int spaceEnd = (145 - pagination.length()) - spaceStart;
                for (int j = 1; j <= spaceStart; j++) {
                    rs += " ";
                }
                rs = rs.concat(pagination.toString());
                for (int j = 1; j <= spaceEnd; j++) {
                    rs += " ";
                }
                rs += "|";
                System.out.println(rs);
                System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|               1. Previous page              |                      2. Back                   |               3. Next page              |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

                int choice = InputMethod.getNumber("Enter choice : ");
                switch (choice) {
                    case 1: {
                        if (currentPage > 1) {
                            currentPage--;
                            skip = (currentPage - 1) * itemPerPage;
                        } else {
                            System.err.println("Cannot previous !");
                        }
                        break;
                    }
                    case 2: {
                        return;
                    }
                    case 3: {
                        if (currentPage < totalPage) {
                            currentPage++;
                            skip = (currentPage - 1) * itemPerPage;
                        } else {
                            System.err.println("Cannot next !");
                        }
                        break;
                    }
                    default: {
                        System.out.println("Choice invalid !");
                    }
                }
            }
        }
    }

    public void searchById(List<Order> orders) {
    }

    public void searchByStatus(List<Order> orders) {
    }

    public void seeOrderDetail() {
    }

    public void updateStatusOrder() {
        int orderId;
        while (true) {
            orderId = InputMethod.getNumber("Enter number id order to update status : ");
            if (orderId <= 0) {
                System.err.println("Enter order id > 0 !");
            } else {
                break;
            }
        }
        if (orderService.updateStatus(orderId)) {
            System.out.println("Update successfully !");
        } else {
            System.err.println("Update error !");
        }
    }

    public void searchOrderByDay(List<Order> orders) {
    }

    public void cancelOrder() {
        int orderId;
        while (true) {
            orderId = InputMethod.getNumber("Enter number id order to update status : ");
            if (orderId <= 0) {
                System.err.println("Enter order id > 0 !");
            } else {
                break;
            }
        }
        orderService.cancelOrder(orderId);
        if (orderService.updateStatus(orderId)) {
            System.out.println("Update successfully !");
        } else {
            System.err.println("Update error !");
        }
    }

    public boolean add() {
        return false;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }
}