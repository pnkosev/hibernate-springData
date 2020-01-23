package pn.advancedquerying.utils;

import java.util.Scanner;

public class ScannerUtil {
    private final static Scanner SCANNER = new Scanner(System.in);

    private ScannerUtil() {}

    public static String nextLine() {
        return SCANNER.nextLine();
    }
}
