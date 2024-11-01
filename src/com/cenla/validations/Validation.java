package com.cenla.validations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    Scanner sc;

    public Validation(Scanner sc) {
        this.sc = sc;
    }

    public int getInt() {
        int x;
        while (true) {
            try {
                x = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Введите целое число");
                sc.nextLine();
            }
        }
        return x;
    }

    public int getPositiveInt() {
        int x;
        while (true) {
            x = getInt();
            if (x < 0) System.out.println("Введите положительное число");
            else break;
        }
        return x;
    }

    public double getDouble() {
        double x;
        while (true) {
            try {
                x = sc.nextDouble();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Введите дробное число");
                sc.nextLine();
            }
        }
        return x;
    }

    public double getPositiveDouble() {
        double x;
        while (true) {
            x = getDouble();
            if (x < 0) {
                System.out.println("Введите положительное число");
            } else {
                break;
            }
        }
        return x;
    }

    public double getRightCondition(int x, int y) {
        double k;
        while (true) {
            k = getPositiveDouble();
            if (k > x && k < y) {
                return k;
            }
            else System.out.println("Введите число в диапазоне от " + x + " до " + y);
        }
    }

    public boolean isRussianLetters(String input) {
        return input.matches("[А-Яа-яЁё\\s]+");
    }


    public String enterText() {
        String text;
        while (true) {
            text = sc.nextLine().trim();
            if (isRussianLetters(text)) {
                return text;
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите только русские буквы.");
            }
        }
    }

    public String enterType(String[] array) {
        String type;
        while (true) {
            type = enterText();
            for (String arr: array) {
                if (type.equalsIgnoreCase(arr)) {
                    return type;
                }
            }
            System.out.println("Некорректный тип");
        }
    }
}
