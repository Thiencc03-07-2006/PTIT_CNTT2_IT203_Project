package presentation.ui;

import model.User;
import presentation.menu.AdminManagement;
import presentation.menu.Shop;
import service.UserService;
import util.InputMethod;
import util.Session;

import java.util.Scanner;

public class LoginUI {
    private static final UserService userService = UserService.getInstance();

    public static void main(String[] args) {
        String username = InputMethod.getString("Enter username: ");

        String password = InputMethod.getString("Enter password: ");

        User user = userService.login(username, password);

        if (user != null) {
            Session.setCurrentUser(user);
            System.out.println("Login success!");
            if (user.getRole().name().equals("ADMIN")) {
                System.out.println("Go to Admin page...");
                AdminManagement.main(args);
            } else {
                System.out.println("Go to Shop...");
                Shop.main(args);
            }
        } else {
            System.err.println("Username or password incorrect!");
        }
    }
}