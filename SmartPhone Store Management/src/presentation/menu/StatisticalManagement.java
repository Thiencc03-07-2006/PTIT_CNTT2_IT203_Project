package presentation.menu;

import model.*;
import util.GetColor;
import util.InputMethod;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StatisticalManagement {
//    public static void main(String[] args) {
//        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
//        List<Category> categories = InputMethod.listCategory();
//        List<Product> products = InputMethod.listProduct();
//        List<Order> orders = InputMethod.listOrder();
//        List<Customer> customers = InputMethod.listCustomer();
//        List<ProductCart> productCarts = InputMethod.listProductCart();
//        int totalCategory = categories.size();
//        int totalProduct = products.size();
//        int totalOrder = orders.size();
//        int totalCustomer = customers.size();
//        int currentMonth = LocalDate.now().getMonthValue();
//        Date date = new Date(); // Lấy thời gian hiện tại
//
//        double salesMonth = orders.stream()
//                .filter(order -> {
//                    LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//                    return localDate.getMonthValue()== currentMonth  &&  order.getStatus() == OrderStatus.DELIVERED;
//                })
//                .toList().stream().map(Order::getTotalMoney).reduce((double) 0,(pre, current) -> pre + current);
//        while (true){
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                                     "+ GetColor.GREEN+"STATISTICAL"+GetColor.RESET+"                                                      |");
//            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|           Total Categories :           |          Total Products :          |               Total Orders :           |");
//            System.out.printf("|                 %-3d Category           |                %-3d Product         |                 %-3d Order              |\n",totalCategory,totalProduct,totalOrder);
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|             Total Customers :          |          Sales this month :        |                                        |");
//            System.out.printf("|                %-3d Customer            |               %-20s |                                        |\n",totalCustomer,format.format(salesMonth)+" VNĐ");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|                                        |                                    |                                        |");
//            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//            System.out.println("|                      1. View monthly sales                 |                   2. Exit                               |");
//            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//            int choice = InputMethod.getNumber("Enter your choice : ");
//            switch (choice){
//                case 1 : {
//                    int currentYear = LocalDate.now().getYear();
//                    String title = GetColor.GREEN+"Year : " + currentYear + GetColor.RESET ;
//                    System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//                    System.out.printf("|                                                 %15s                                                          |\n",title);
//                    System.out.println("┏━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//                    System.out.println("|    Month          |     Total order     |   Total order cancel   |   Total order success  |        Total money       |");
//                    System.out.println("|━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//                    List<Order> rs = orders.stream().filter(order -> {
//                        LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//                        return localDate.getYear() == currentYear;
//                    }).toList();
//                    for(int i = 1 ; i <= 12 ; i++){
//                        String month = "Month " + i ;
//                        int finalI = i;
//                        int orderTotal = rs.stream().filter(order -> {
//                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//                            return localDate.getMonthValue() == finalI;
//                        }).toList().size();
//
//                        int orderCancel = rs.stream().filter(order -> {
//                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//                            return localDate.getMonthValue() == finalI &&  order.getStatus() == OrderStatus.CANCELLED;
//                        }).toList().size();
//
//                        int orderSuccess = rs.stream().filter(order -> {
//                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//                            return localDate.getMonthValue() == finalI &&  order.getStatus() == OrderStatus.DELIVERED;
//                        }).toList().size();
//
//                        double totalMoney = rs.stream().filter(order -> {
//                            LocalDate localDate = order.getCreatedDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//                            return localDate.getMonthValue() == finalI &&  order.getStatus() == OrderStatus.DELIVERED;
//                        }).toList().stream().map(Order::getTotalMoney).reduce((double) 0, Double::sum);
//
//                        System.out.println("|━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//                        System.out.printf("| %-17s | %-19d | %-22d | %-22d | %-24s |\n",month,orderTotal,orderCancel,orderSuccess,format.format(totalMoney)+" VNĐ");
//                        System.out.println("|━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//                    }
//                    System.out.println("|                                                      0. Back                                                         |");
//                    System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//                    while (true){
//                        try {
//                            System.out.println("Enter 0 to back !");
//                            int exit = Integer.parseInt(InputMethod.scanner.nextLine().trim());
//                            if(exit == 0){
//                                break;
//                            }else {
//                                System.err.println("Please enter number 0 !");
//                            }
//                        }catch (NumberFormatException e){
//                            System.err.println("Input invalid !");
//                        }
//                    }
//
//                    break;
//                }
//                case 2 : {
//                    return;
//                }
//                default: {
//                    System.err.println("Enter choice 1 or 2 !");
//                }
//            }
//        }
//    }
}