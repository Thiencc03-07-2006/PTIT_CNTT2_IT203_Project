package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetColor {
    public static final   List<String> colors = new ArrayList<>(Arrays.asList("Black","White","Red","Yellow","Blue","Green","Purple","Orange"));
    // Định nghĩa các mã ANSI escape codes cho các màu chữ
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
}