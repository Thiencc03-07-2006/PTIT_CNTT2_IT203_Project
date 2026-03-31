package util;

import lombok.Getter;
import lombok.Setter;
import model.User;

//@Setter
//@Getter
public class Session {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLogin() {
        return currentUser != null;
    }
}