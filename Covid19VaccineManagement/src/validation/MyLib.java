/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author macos
 */
public class MyLib {

    final private static Scanner sc = new Scanner(System.in);

    /**
     * kiểm tra việc nhập choice ở menu
     *
     * @param inputMsg
     * @param errorMsg
     * @param min
     * @param max
     * @return
     */
    public static int getChoice(String inputMsg, String errorMsg, int min, int max) {
        if (min > max) {
            int tmp = min;
            min = max;
            max = tmp;
        }
        do {
            try {
                System.out.print(inputMsg);
                int n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (true);
    }

    /**
     * kiểm tra việc nhập một chuỗi theo format qui định
     *
     * @param inputMsg
     * @param errorMsg
     * @param format
     * @return
     */
    public static String getAString(String inputMsg, String errorMsg, String format) {
        while (true) {
            System.out.print(inputMsg);
            String s = sc.nextLine().trim();
            if (s.length() == 0 || s.isEmpty() || s.matches(format) == false) {
                System.out.println(errorMsg);
            } else {
                return s;
            }
        }
    }

    /**
     * kiểm tra việc nhập ngày tháng năm theo kiểu String
     *
     * @param inputMsg
     * @return
     */
    public static String getDate(String inputMsg) {
        String regex = "^\\d{2}[ -|/]\\d{2}[ -|/]\\d{4}$";
        while (true) {
            System.out.print(inputMsg);
            String date = sc.nextLine().trim();
            if (date.matches(regex)) {
                int d = Integer.parseInt(date.substring(0, 2));
                int m = Integer.parseInt(date.substring(3, 5));
                int y = Integer.parseInt(date.substring(6));
                if (validDate(d, m, y)) {
                    return date.substring(0, 2) + "/" + date.substring(3, 5) + "/" + date.substring(6);
                } else {
                    System.out.println("Invalid date!");
                    continue;
                }
            }
            if (date.length() == 0 || date.isEmpty()) {
                System.out.println("Don't accept empty or blank!");
                continue;
            }
            System.out.println("Invalid date or Wrong format! the format is dd/mm/yyyy");
        }
    }

    /**
     * kiểm tra việc nhập ngày tháng năm
     *
     * @param d
     * @param m
     * @param y
     * @return
     */
    private static boolean validDate(int d, int m, int y) {
        int maxd = 31;
        if ((d < 1) || (d > 31) || (m < 1) || (m > 12)) {
            return false;
        }
        if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
            maxd = 30;
        }
        if (m == 2) {
            if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0))) {
                maxd = 29;
            } else {
                maxd = 28;
            }
        }
        return (d <= maxd);
    }

    /**
     * kiểm tra nhập chuỗi không cần định dạng
     *
     * @param inputMsg
     * @param errorMsg
     * @return
     */
    public static String getAString(String inputMsg, String errorMsg) {
        while (true) {
            System.out.print(inputMsg);
            String s = sc.nextLine().trim();
            if (s.length() == 0 || s.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return s;
            }
        }
    }

    /**
     * hỏi người dùng có muốn tiếp tục một chức năng nào đó hay không(yes/no)
     *
     * @param inputMsg
     * @return
     */
    public static boolean isContinue(String inputMsg) {
        while (true) {
            System.out.print(inputMsg);
            String pattern = sc.nextLine().trim();
            if (pattern.length() == 0 || pattern.isEmpty()) {
                System.err.println("Don't accept empty or blank!");
                continue;
            }
            if (pattern.equalsIgnoreCase("y") || pattern.equalsIgnoreCase("yes") || pattern.equalsIgnoreCase("n") || pattern.equalsIgnoreCase("no")) {
                if (pattern.equalsIgnoreCase("y") || pattern.equalsIgnoreCase("yes")) {
                    return true;
                }
                if (pattern.equalsIgnoreCase("n") || pattern.equalsIgnoreCase("no")) {
                    return false;
                }
            } else {
                System.out.println("Only choice Yes(Y) or No(N)");
            }
        }
    }

    /**
     * kiểm tra khoảng cách giữa 2 date
     *
     * @param date1
     * @param date2
     * @param minDistanceDate
     * @param maxDistanceDate
     * @return
     */
    public static boolean checkDistance2Date(String date1, String date2, int minDistanceDate, int maxDistanceDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            long getDiff = format.parse(date2).getTime() - format.parse(date1).getTime();
            long getDaysDiff = getDiff / (24 * 60 * 60 * 1000);
            if (getDaysDiff >= minDistanceDate && getDaysDiff <= maxDistanceDate) {
                return true;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date or Wrong format!");
        }
        return false;
    }

}
