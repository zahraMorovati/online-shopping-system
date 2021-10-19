package validData;

import myDate.MyDate;

import java.util.InputMismatchException;
import java.util.Scanner;

import static validData.ConsoleColors.*;


public class GetValidData {

    public static Scanner input = new Scanner(System.in);

    public static String getValidName(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String name = input.next();
        if (name.matches("[a-zA-Z]*")) {
            return name;
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidName(message);
        }
    }

    public static MyDate getValidBirthDate() {
        try {
            System.out.print(BLUE_BRIGHT + "enter birth date (year month day):" + RESET);
            MyDate birthDate = new MyDate(input.nextInt(), input.nextInt(), input.nextInt());
            if(MyDate.isValidDate(birthDate.getYear(),birthDate.getMonth(),birthDate.getDay())){
                return birthDate;
            }else {
                System.out.println(RED + "invalid date!" + RESET);
                return getValidBirthDate();
            }

        } catch (InputMismatchException e) {
            System.out.println(RED + "invalid date!" + RESET);
            return getValidBirthDate();
        }
    }

    public static MyDate getValidDate(String message) {
        try {
            System.out.print(BLUE_BRIGHT + message + " (year month day):" + RESET);
            MyDate birthDate = new MyDate(input.nextInt(), input.nextInt(), input.nextInt());
            return birthDate;
        } catch (InputMismatchException e) {
            System.out.println(RED + "invalid date!" + RESET);
            return getValidBirthDate();
        }
    }

    public static String getValidPhoneNumber(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String phoneNumber = input.next();
        String str = phoneNumber.substring(0);
        if (isNumeric(str)) {
            return phoneNumber;
        } else {
            System.out.println(RED + "invalid phone number!" + RESET);
            return getValidPhoneNumber(message);
        }
    }

    public static int getValidInt(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Integer.parseInt(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidInt(message);
        }
    }

    public static double getValidDouble(String message) {
        System.out.print(BLUE_BRIGHT + message + RESET);
        String str = input.next();
        if (isNumeric(str)) {
            return Double.parseDouble(str);
        } else {
            System.out.println(RED + "invalid input!" + RESET);
            return getValidDouble(message);
        }
    }



    public static int getValidChoice(String message, int maxChoice) {
        int number = getValidInt(message);
        for (int i = 1; i < maxChoice + 1; i++) {
            if (i == number) {
                return number;
            }
        }
        System.out.println(RED + "invalid choice!" + RESET);
        return getValidChoice(message, maxChoice);
    }

    public static boolean isNumeric(String str) {

        if (str == null || str.length() == 0) {
            return false;
        }

        try {
            Double.parseDouble(str);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }

    }



}
