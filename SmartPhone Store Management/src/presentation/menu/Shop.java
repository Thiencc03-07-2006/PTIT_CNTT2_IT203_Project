package presentation.menu;

import model.Product;
import presentation.ui.CartUI;
import presentation.ui.HistoryOrder;
import presentation.ui.ShopUI;
import service.ProductService;
import util.InputMethod;
import util.Session;

import java.util.List;

public class Shop {

    public static void main(String[] args) {
        ShopUI shopFeature = new ShopUI();
        CartUI cartFeature = new CartUI();
        HistoryOrder historyOrder = new HistoryOrder();
        while (true) {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("|   Hello , %-28s |\n", Session.isLogin() ? Session.getCurrentUser().getUserName() + " !" : "Customer !");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━ SHOPPING ━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|        1. Display list products        |    2. View personal information    |              3. View cart              |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                          |                               |                               |                           |");
            System.out.println("|     4. Orders history    |       5. Favorites list       |    6. Display list address    |    7. Logout / Login      |");
            System.out.println("|                          |                               |                               |                           |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

            int choice = InputMethod.getNumber("Enter choice : ");
            switch (choice) {
                case 1: {
                    shopFeature.displayList();
                    break;
                }
                case 2: {
//                    informationFeature.viewInformation();
                    break;
                }
                case 3: {
                    cartFeature.viewCart();
                    break;
                }
                case 4: {
                    historyOrder.displayList();
                    break;
                }
                case 5: {
//                    Customer customer = InputMethod.checkLogin().getFirst();
//                    if(customer == null){
//                        System.err.println("Please log in first !");
//                    }else {
//                        favoritesFeature.displayFavorites(customer);
//                    }
                    break;
                }
                case 6: {
//                    Customer customer = InputMethod.checkLogin().getFirst();
//                    if(customer == null){
//                        System.err.println("Please log in first !");
//                    }else {
//                        addressFeature.displayListAddress();
//                    }
                    break;
                }
                case 7: {
                    Session.logout();
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}