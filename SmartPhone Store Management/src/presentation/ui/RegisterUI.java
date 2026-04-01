package presentation.ui;

import model.User;
import service.UserService;
import util.InputMethod;


public class RegisterUI {
    private static final UserService userService = UserService.getInstance();

    public static void main(String[] args) {
        User user = new User();

        while (true) {
            String name = InputMethod.getString("Username: ");
            if (userService.existsByUsername(name)) {
                System.out.println("Username already exists!");
            } else {
                user.setUserName(name);
                break;
            }
        }

        while (true) {
            String email = InputMethod.getString("Email: ");

            if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                System.out.println("Invalid email!");
            } else if (userService.existsByEmail(email)) {
                System.out.println("Email already exists!");
            } else {
                user.setEmail(email);
                break;
            }
        }

        while (true) {
            String phone = InputMethod.getString("Phone: ");

            if (!phone.matches("0\\d{9}")) {
                System.out.println("Invalid phone!");
            } else if (userService.existsByPhone(phone)) {
                System.out.println("Phone already exists!");
            } else {
                user.setPhoneNumber(phone);
                break;
            }
        }

        while (true) {
            String password = InputMethod.getString("Password: ");

            if (password.length() < 8) {
                System.out.println("At least 8 characters!");
            } else {
                user.setPassword(password);
                break;
            }
        }

        user.setAddress(InputMethod.getString("Address: "));

        if (userService.register(user)) {
            System.out.println("Register success!");
        } else {
            System.out.println("Register failed!");
        }
    }
}