package com.cenla.menu;

import com.cenla.validations.Validation;

import java.util.Scanner;

public class Menu {

    Scanner sc;
    Validation validation;
    int choice = 0;

    public Menu(Scanner sc, Validation validation) {
        this.sc = sc;
        this.validation = validation;
    }

    public int menuSimulation() {
        while (true) {
            System.out.println("1 - Добавить\n2 - Просмотр\n3 - Редактировать\n4" +
                    " - Удалить\n5 - Предсказания\n6 - Выход");
            choice = validation.getPositiveInt();
            if (choice >= 1 && choice <= 6) {
                return choice;
            }
        }
    }

    public int mainMenu() {
        while (true) {
            System.out.println("1 - Создать симуляцию\n2 - Продолжить работу с существующими симуляциями\n3 - Выход");
            choice = validation.getPositiveInt();
            if (choice >= 1 && choice <= 3) {
                return choice;
            }
        }
    }

    public int AnimalsAndPlantsMenu() {
        while (true) {
            System.out.println("1 - Животное\n2 - Растение\n3 - Выход");
            choice = validation.getPositiveInt();
            if (choice >= 1 && choice <= 3) {
                return choice;
            }
        }
    }

    public int editingAnimalsMenu() {
        while (true) {
            System.out.println("1 - Изменить тип\n2 - Изменить вид\n3 - Изменить среду обитания\n4 - Изменить количество" +
                    "\n5 - Изменить ресурсы и климатические условия\n6 - Выход");
            choice = validation.getPositiveInt();
            if (choice >= 1 && choice <= 6) {
                return choice;
            }
        }
    }

    public int editingPlantsMenu() {
        while (true) {
            System.out.println("1 - Изменить тип\n2 - Изменить вид\n\n3 - Изменить количество" +
                    "\n4 - Изменить ресурсы и климатические условия\n5 - Выход");
            choice = validation.getPositiveInt();
            if (choice >= 1 && choice <= 5) {
                return choice;
            }
        }
    }

    public int conditionsMenu() {
        while (true) {
            System.out.println("1 - Изменить среднюю температуру\n2 - Изменить среднюю влажность\n3 - Изменить среднее количество воды\n4 - Выход");
            choice = validation.getPositiveInt();
            if (choice >= 1 && choice <= 4) {
                return choice;
            }
        }
    }
}
