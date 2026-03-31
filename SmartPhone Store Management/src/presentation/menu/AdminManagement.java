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
            System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━|");
            System.out.println("|                             |                              |                              |                          |");
            System.out.println("|   4. Customers management   |        5. Statistical        |         6. List role         |         7. Logout        |");
            System.out.println("|                             |                              |                              |                          |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

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
//                    displayRole();
                    break;
                }
                case 7 : {
                    Session.logout();
                    return;
                }
                default: {
                    System.out.println("Enter choice from 1 to 6 !");
                }
            }
        }
    }

//    public static void displayRole(){
//        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//        System.out.println("|                                     "+ GetColor.GREEN+"LIST ROLE"+GetColor.RESET+"                               |");
//        System.out.println("┏━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//        System.out.println("|     STT     |                         Role Name                             |");
//        int i = 1 ;
//        for(Role role : Role.values()){
//            System.out.println("|━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//            System.out.printf("|      %-6d | %-61s |\n",i,role);
//            System.out.println("|━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//            i++ ;
//        }
//        System.out.println("|━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
//        System.out.println("|                                     0. Back                                 |");
//        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//        int choice ;
//        while (true){
//            try {
//                System.out.println("Enter 0 to back !");
//                choice = Integer.parseInt(InputMethod.scanner.nextLine().trim());
//                if(choice == 0){
//                    break;
//                }else {
//                    System.err.println("Try enter !");
//                }
//            }catch (NumberFormatException e){
//                System.err.println("Input invalid !");
//            }
//        }
//    }
}