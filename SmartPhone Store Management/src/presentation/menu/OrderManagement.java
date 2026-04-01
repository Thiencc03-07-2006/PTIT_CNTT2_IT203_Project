package presentation.menu;


import model.Order;
import presentation.ui.OrderUI;
import util.GetColor;
import util.InputMethod;

import java.util.List;

public class OrderManagement {
    public static void main(String[] args) {
        OrderUI orderFeature = new OrderUI();
        while (true) {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                                            " + GetColor.GREEN + "ORDER MANAGEMENT" + GetColor.RESET + "                                                            |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                               |                                                         |                              |");
            System.out.println("|            1. Display list orders             |                2. See order detail by id                |    3. Update status order    |");
            System.out.println("|                                               |                                                         |                              |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                                               |                                                         |                              |");
            System.out.println("|               4. Cancel order                 |              5. Search order by day a -> b              |           6. Back            |");
            System.out.println("|                                               |                                                         |                              |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = InputMethod.getNumber("Enter your choice : ");
            switch (choice) {
                case 1: {
                    orderFeature.displayList();
                    break;
                }
                case 2: {
                    orderFeature.seeOrderDetail();
                    break;
                }
                case 3: {
                    orderFeature.updateStatusOrder();
                    break;
                }
                case 4: {
                    orderFeature.cancelOrder();
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    return;
                }
                default: {
                    System.out.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}