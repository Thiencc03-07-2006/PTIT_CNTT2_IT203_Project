package util;

import model.*;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputMethod {
    public static Scanner scanner = new Scanner(System.in);

    public static int getNumber(String label) {
        int number = 0;
        while (true) {
            try {
                System.out.print(label);
                number = Integer.parseInt(scanner.nextLine().trim());
                if (number >= 0) {
                    break;
                } else {
                    System.out.println("Enter number >= 0 !");
                }
            } catch (Exception e) {
                System.out.println("Number invalid !");
            }
        }
        return number;
    }

    public static double getDouble(String label) {
        double number = 0;
        while (true) {
            try {
                System.out.print(label);
                number = Double.parseDouble(scanner.nextLine().trim());
                if (number > 0) {
                    break;
                } else {
                    System.out.println("Enter number > 0 !");
                }
            } catch (Exception e) {
                System.out.println("Number invalid !");
            }
        }
        return number;
    }

    public static String getString(String label) {
        String rs;
        while (true) {
            System.out.print(label);
            rs = InputMethod.scanner.nextLine().trim();
            if (rs.isEmpty()) {
                System.out.println("Cannot be left blank !");
            } else {
                break;
            }
        }
        return rs;
    }

    public static String getStringOptional(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }
}