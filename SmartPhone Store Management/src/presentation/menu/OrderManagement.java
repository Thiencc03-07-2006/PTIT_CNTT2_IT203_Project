package presentation.menu;


import model.Order;
import presentation.ui.OrderUI;
import util.GetColor;
import util.InputMethod;

import java.util.List;

public class OrderManagement {
    public static void main(String[] args) {
        OrderUI orderFeature = new OrderUI();
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                            "+ GetColor.GREEN+"ORDER MANAGEMENT"+GetColor.RESET+"                                                            |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("|      1. Display list orders   |       2. Search order by id         |      3. Search order by status    |  4. See order detail by id   |");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("|      5. Update status order   |            6. Cancel order          |   7. Search order by day a -> b   |           8. Back            |");
            System.out.println("|                               |                                     |                                   |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = InputMethod.getNumber("Enter your choice : ");
            switch (choice){
                case 1 : {
                    orderFeature.displayList();
                    break;
                }
                case 2 : {
//                    orderFeature.searchById(InputMethod.listOrder());
                    break;
                }
                case 3 : {
//                    orderFeature.searchByStatus(InputMethod.listOrder());
                    break;
                }
                case 4 : {
//                    orderFeature.seeOrderDetail();
                    break;
                }
                case 5 : {
                    orderFeature.updateStatusOrder();
                    break;
                }
                case 6 : {
//                    orderFeature.cancelOrder();
                    break;
                }
                case 7 : {
//                    orderFeature.searchOrderByDay(InputMethod.listOrder());
                    break;
                }
                case 8 : {
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}