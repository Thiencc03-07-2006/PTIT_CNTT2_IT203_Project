package service;

import dao.UserDAO;
import model.Role;
import model.User;
import util.PasswordBCrypt;

import java.util.Date;

public class UserService {
    private static UserService instance;
    private final UserDAO userDAO = UserDAO.getInstance();

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean existsByUsername(String username) {
        return userDAO.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userDAO.existsByUsername(email);
    }

    public boolean existsByPhone(String phone) {
        return userDAO.existsByUsername(phone);
    }

    public boolean register(User user) {

        if (userDAO.existsByUsername(user.getUserName())) {
            System.out.println("Username already exists!");
            return false;
        }

        // hash password
        user.setPassword(PasswordBCrypt.hashPassword(user.getPassword()));

        return userDAO.save(user);
    }

    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null) {
            if (!user.getStatus()) {
                System.out.println("Account is locked!");
                return null;
            }
            if (PasswordBCrypt.checkPassword(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}