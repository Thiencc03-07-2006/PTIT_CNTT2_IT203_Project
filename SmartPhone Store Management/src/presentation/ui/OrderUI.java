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
//        int orderId = InputMethod.getNumber("Enter id order want search : ");
//        Order rs = orders.stream().filter(order -> Objects.equals(order.getOrderId(), orderId)).findFirst().orElse(null);
//        if(rs == null){
//            System.err.println("Not found order id !");
//        }else {
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                                           "+GetColor.GREEN+"RESULT SEARCH"+GetColor.RESET+"                                                                |");
//            rs.displayData();
//            productCartFeature.displayList(rs.getCarts());
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                                             0. Back                                                                    |");
//            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//            System.out.println("                                                       *                  *                                                               ");
//            System.out.println("                                                                 *                                                                        ");
//        }
    }

    public void searchByStatus(List<Order> orders) {
//        Integer status ;
//        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//        System.out.println("|       0. Cancel        |       1. Ordered       |       2. Confirm       |      3. Delivering      |      4. Delivered       |");
//        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");
//        while (true){
//            try {
//                System.out.println("Enter status want search : ");
//                status = Integer.parseInt(scanner.nextLine().trim());
//                if(status >= 0 && status < 5){
//                    break;
//                }else {
//                    System.err.println("Enter status from 0 to 4 !");
//                }
//            }catch (NumberFormatException n){
//                System.err.println("Status invalid !");
//            }
//        }
//        Integer finalStatus = status;
//        List<Order> rs = orders.stream().filter(order -> Objects.equals(order.getStatus(), finalStatus)).toList();
//        if(rs.isEmpty()){
//            System.err.println("Not found order with status = " + status);
//        }else {
//            System.out.println("Result search status = " + status);
//            displayList(rs);
//        }
    }

    public void seeOrderDetail() {
//        List<Order> orders = InputMethod.listOrder();
//        int orderId ;
//        while (true){
//            try {
//                System.out.println("Enter number id order : ");
//                orderId = Integer.parseInt(scanner.nextLine().trim());
//                if(orderId <= 0){
//                    System.err.println("Enter order id > 0 !");
//                }else {
//                    break;
//                }
//            }catch (NumberFormatException n){
//                System.err.println("Order id invalid !");
//            }
//        }
//        Integer finalOrderId = orderId;
//        Order rs = orders.stream().filter(order -> Objects.equals(order.getOrderId(), finalOrderId)).findFirst().orElse(null);
//        if(rs == null){
//            System.err.println("Not found order id !");
//        }else {
////            productCartFeature.displayList(rs.getCarts());
//
//        }

    }

    public void updateStatusOrder() {
        int orderId ;
        while (true){
            try {
                System.out.println("Enter number id order to update status : ");
                orderId = Integer.parseInt(scanner.nextLine().trim());
                if(orderId <= 0){
                    System.err.println("Enter order id > 0 !");
                }else {
                    break;
                }
            }catch (NumberFormatException n){
                System.err.println("Order id invalid !");
            }
        }
        int index = orders.stream().map(Order::getOrderId).toList().indexOf(orderId);
        if(index == -1){
            System.err.println("Not found order id !");
        }else {
            boolean isSuccess = orders.get(index).updateStatus();
            if(isSuccess){
                System.out.println("Update successfully !");
                InputMethod.saveDatabase(fileName,orders);
            }else {
                System.err.println("Update error !");
            }
        }
    }

    public void searchOrderByDay(List<Order> orders) {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date startDay ;
//        Date endDay ;
//        while (true){
//            System.out.println("Enter start day (dd/MM/yyyy) : ");
//            String day = InputMethod.scanner.nextLine().trim();
//            if(day.matches("^[0-3][0-9]/[01][0-9]/[0-9]{4}$")){
//                List<String> arr = new ArrayList<>(Arrays.asList(day.split("/")));
//                LocalDate localDate = LocalDate.of(Integer.parseInt(arr.get(2)),Integer.parseInt(arr.get(1)),1);
//                int dayOfMonth = localDate.lengthOfMonth();
//                if(Integer.parseInt(arr.get(1)) > 12 && Integer.parseInt(arr.get(1)) > 0){
//                    System.err.println("Month invalid (0 < month < 12) !");
//                }else {
//                    if(Integer.parseInt(arr.get(0)) > dayOfMonth){
//                        System.err.println("Month " + arr.get(1) + " have " + dayOfMonth + " day !");
//                    }else {
//                        try {
//                            startDay = simpleDateFormat.parse(day);
//                            break;
//                        } catch (ParseException e) {
//                            System.err.println("Input invalid !");
//                        }
//                    }
//                }
//            }else {
//                System.err.println("Start day invalid !");
//            }
//        }
//
//        while (true){
//            System.out.println("Enter end day (dd/MM/yyyy) : ");
//            String day = InputMethod.scanner.nextLine().trim();
//            if(day.matches("^[0-3][0-9]/[01][0-9]/[0-9]{4}$")){
//                List<String> arr = new ArrayList<>(Arrays.asList(day.split("/")));
//                LocalDate localDate = LocalDate.of(Integer.parseInt(arr.get(2)),Integer.parseInt(arr.get(1)),1);
//                int dayOfMonth = localDate.lengthOfMonth();
//                if(Integer.parseInt(arr.get(1)) > 12 && Integer.parseInt(arr.get(1)) > 0){
//                    System.err.println("Month invalid (0 < month < 12) !");
//                }else {
//                    if(Integer.parseInt(arr.get(0)) > dayOfMonth){
//                        System.err.println("Month " + arr.get(1) + " have " + dayOfMonth + " day !");
//                    }else {
//                        try {
//                            endDay = simpleDateFormat.parse(day);
//                            if (! (startDay.compareTo(endDay) > 0)){
//                                break;
//                            }else {
//                                System.err.println("Enter end day > start day ! ");
//                            }
//
//                        } catch (ParseException e) {
//                            System.err.println("Input invalid !");
//                        }
//                    }
//                }
//            }else {
//                System.err.println("End day invalid !");
//            }
//        }
//
//        Date finalStartDay = startDay;
//        Date finalEndDay = endDay;
//        orders = orders.stream()
//                .filter(order -> order.getCreatedDate().compareTo(finalStartDay) >= 0 && order.getCreatedDate().compareTo(finalEndDay) <= 0).toList();
//
//        if(orders.isEmpty()){
//            System.err.println("Not found order !");
//        }else {
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                                           "+GetColor.GREEN+"RESULT SEARCH"+GetColor.RESET+"                                                                |");
//            displayList(orders);
//        }
//
//
//    }
//    public void cancelOrder(){
//        List<Order> orders = InputMethod.listOrder();
//        int idOrder = InputMethod.getNumber("Enter id order to cancel : ");
//        int indexOrder = orders.stream().map(Order::getOrderId).toList().indexOf(idOrder);
//        if(indexOrder == -1){
//            System.err.println("Not found id order ! ");
//        }else {
//            if(orders.get(indexOrder).getStatus() != OrderStatus.DELIVERED){
//                orders.get(indexOrder).setStatus(OrderStatus.CANCELLED);
//                InputMethod.saveDatabase(InputMethod.fileOrder,orders);
//                List<Product> products = InputMethod.listProduct();
//                for(ProductCart productCart : orders.get(indexOrder).getCarts()){
//                    String productName = productCart.getProductName();
//                    int indexProduct = products.stream().map(Product::getProductName).toList().indexOf(productName);
//                    products.get(indexProduct).setInventory(products.get(indexProduct).getInventory()+productCart.getQuantity());
//                }
//                InputMethod.saveDatabase(InputMethod.fileProduct,products);
//                System.out.println("Cancel order success !");
//            }else {
//                System.err.println("Cannot cancel this order when order status =  " + orders.get(indexOrder).printStatus(orders.get(indexOrder).getStatus()));
//            }
//
//        }
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