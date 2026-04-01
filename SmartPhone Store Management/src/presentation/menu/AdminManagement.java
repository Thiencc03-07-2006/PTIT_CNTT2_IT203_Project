package presentation.menu;

import model.Role;
import util.GetColor;
import util.InputMethod;
import util.Session;

public class AdminManagement {
    public static void main(String[] args) {
        while (true){
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("| %-36s ! |\n",Session.getCurrentUser().getUserName());
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|━━━━━━━ ADMIN MANAGEMENT ━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|      1. Categories management          |      2. Products management        |           3. Orders management         |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("|      4. Customers management           |           5. Statistical           |                7. Logout               |");
            System.out.println("|                                        |                                    |                                        |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n");

            int choice = InputMethod.getNumber("Enter choice : ");
            switch (choice){
                case 1 : {
                    CategoriesManagement.main(args);
                    break;
                }
                case 2 : {
                    ProductManagement.main(args);
                    break;
                }
                case 3 : {
                    OrderManagement.main(args);
                    break;
                }
                case 4 : {
//                    CustomerManagement.main(args);
                    break;
                }
                case 5 : {
//                    StatisticalManagement.main(args);
                    break;
                }
                case 6 : {
                    Session.logout();
                    return;
                }
                default: {
                    System.out.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }
}