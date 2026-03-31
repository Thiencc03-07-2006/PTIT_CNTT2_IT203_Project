package presentation.menu;

import model.User;
import presentation.ui.LoginUI;
import presentation.ui.RegisterUI;
import util.GetColor;
import util.InputMethod;

import java.util.List;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) {
//        Login login = new Login();
//        Scanner scanner = new Scanner(System.in);
//        List<User> checkLogin = InputMethod.checkLogin();
//        if(checkLogin.getFirst() != null){
//            InputMethod.logout();
//        }
        while (true) {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("|     " + GetColor.GREEN + "WELCOME TO SHOP : IPHONE STORE" + GetColor.RESET + "                                             |                                            |");
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━ HOME PAGE ━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                      ┃                        ┃                           ┃                        ┃                       |");
            System.out.println("┃    1. REGISTER       ┃       2. LOGIN         ┃        3. Shopping        ┃   4. FORGET PASSWORD   ┃        5 . EXIT       |");
            System.out.println("┃                      ┃                        ┃                           ┃                        ┃                       |");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━┛");
            int choice = InputMethod.getNumber("Enter your choice : ");
            switch (choice) {
                case 1: {
                    RegisterUI.main(args);
                    break;
                }
                case 2: {
                    LoginUI.main(args);
                    break;
                }
                case 3: {
                    Shop.main(args);
                    break;
                }
                case 4: {
//                    ForgetPassword forgetPassword = new ForgetPassword();
//                    forgetPassword.forgetPassword();
                    break;
                }
                case 5: {
                    System.out.println("Goodbye !!!");
                    return;
                }
                default: {
                    System.err.println("Enter choice from 1 to 5 !");
                }
            }
        }
    }
}