package presentation.ui;

import model.Order;
import model.OrderDetail;
import model.ProductCart;
import service.OrderService;
import service.ProductService;
import util.GetColor;
import util.InputMethod;
import util.Session;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HistoryOrder {
    private final OrderService orderService = OrderService.getInstance();
    private final OrderUI orderUi=new OrderUI();

    public void seeOrderDetail() {
        int orderId = InputMethod.getNumber("Enter order id : ");
        Order order = orderService.findOrderById(orderId);
        if (order == null) {
            System.out.println("Not found order id !");
        } else {
            int currentPage = 1;
            int itemPerPage = 5;
            while (true) {
                List<OrderDetail> orderDetails = orderService.getOrderDetails(orderId);
                if (orderDetails.isEmpty()) {
                    System.out.println("Order empty !");
                    break;
                } else {
                    int skip = (currentPage - 1) * itemPerPage;
                    int totalPage = (int) Math.ceil((double) orderDetails.size() / itemPerPage);
                    int size = orderDetails.size();
                    displayOrderDetail(skip, itemPerPage, size, orderDetails, currentPage, totalPage);
                    int choice = InputMethod.getNumber("Enter your choice : ");
                    switch (choice) {
                        case 1: {
                            if (currentPage > 1) {
                                currentPage--;
                                skip = (currentPage - 1) * itemPerPage;
                            } else {
                                System.out.println("Cannot previous !");
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
                                System.out.println("Cannot next !");
                            }
                            break;
                        }
                        default: {
                            System.out.println("Enter choice from 1 to 6 !");
                        }
                    }
                }
            }

        }
    }

    public void displayList() {
        if (!Session.isLogin()) {
            System.out.println("Please log in first !");
            return;
        }
        List<Order> orders = orderService.getMyOrders(Session.getCurrentUser().getUserId());
        int currentPage = 1;
        int itemPerPage = 5;
        int totalPage = (int) Math.ceil((double) orders.size() / itemPerPage);
        int skip = (currentPage - 1) * itemPerPage;
        int size = orders.size();
        if (orders.isEmpty()) {
            System.out.println("List orders is empty !");
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
                System.out.println("|              1. Previous page               |                      2. Back                   |               3. Next page              |");
                System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("|        4. Search order by day a -> b        |           5. See order detail by id            |             6. Cancel order             |");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

                int choice = InputMethod.getNumber("Enter choice : ");
                switch (choice) {
                    case 1: {
                        if (currentPage > 1) {
                            currentPage--;
                            skip = (currentPage - 1) * itemPerPage;
                        } else {
                            System.out.println("Cannot previous !");
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
                            System.out.println("Cannot next !");
                        }
                        break;
                    }
                    case 4: {
                        break;
                    }
                    case 5: {
                        seeOrderDetail();
                        break;
                    }
                    case 6: {
                        orderUi.cancelOrder();
                        break;
                    }
                    default: {
                        System.out.println("Enter choice from 1 to 6 !");
                    }
                }
            }
        }

    }

    private void displayOrderDetail(int skip, int itemPerPage, int size, List<OrderDetail> orderDetails, int currentPage, int totalPage) {
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("|                                                              " + GetColor.GREEN + "ORDER DETAIL" + GetColor.RESET + "                                                              |");
        System.out.println("┏━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━┳━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-3s | %-28s | %-25s | %-5s | %-8s | %-18s | %-29s |\n", "ID", "Product Name", "Price", "Qty", "Color", "Storage", "Total");
        System.out.println("┗━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━┻━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        for (int i = skip; i < (skip + itemPerPage); i++) {
            if (i < size) {
                orderDetails.get(i).displayData();
            } else {
                break;
            }
        }
        StringBuilder pagination = new StringBuilder();
        int startPage = Math.max(currentPage - 2, 1);
        int endPage = Math.min(currentPage + 2, totalPage);
        for (int i = startPage; i <= endPage; i++) {
            if (currentPage == i) {
                pagination.append(GetColor.RED + "[" + i + "]" + GetColor.RESET);
            } else {
                pagination.append("[" + i + "]");
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
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.printf("| %-43s | %-42s | %-43s |\n", "1. Previous page", "2. Back", "3. Next page");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
    }
}
