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
                System.out.println("Некорректный ввод. Введите число");
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
}
