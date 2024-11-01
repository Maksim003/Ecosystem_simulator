package com.cenla;

import com.cenla.interfaces.Organism;
import com.cenla.menu.Menu;
import com.cenla.models.Animals;
import com.cenla.models.Conditions;
import com.cenla.models.Plants;
import com.cenla.simulations.Simulation;
import com.cenla.validations.Validation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Validation validation = new Validation(sc);
    static Menu menu = new Menu(sc, validation);
    static File file = new File("simulations.txt");
    static Simulation simulation = new Simulation(file);
    static final String[] animalTypes = {"Млекопитающее", "Птица", "Рептилия", "Амфибия", "Рыба", "Беспозвоночное"};
    static final String[] plantTypes = {"Дерево", "Кустарник", "Трава", "Цветок", "Водное растение"};

    public static void main(String[] args) {
        int choice;
        Integer id;
        createFile();
        while (true) {
            choice = menu.mainMenu();
            switch (choice) {
                case 1:
                    id = simulation.getLastSimulationId();
                    createSimulation(id);
                    if (simulation.getName() != null) simulation.saveSimulation();
                    break;
                case 2:
                    String name = getInputStringWithExit("Введите имя симуляции: ");
                    if (name == null) return;
                    System.out.println("Введите id: ");
                    int idSimulation = validation.getPositiveInt();
                    simulation = simulation.loadSimulation(name, idSimulation);
                    if (simulation.isEmpty()) {
                        System.out.println("Симуляция с именем '" + name + "' и с id '" + idSimulation + "' не найдена");
                    } else {
                        workWithExistingSimulation();
                        id = simulation.getLastSimulationId();
                        simulation.setId(id + 1);
                        simulation.saveSimulation();
                    }
                    break;
                case 3:
                    return;
            }

        }
    }

    public static void createSimulation(Integer id) {
        String name = getInputStringWithExit("Введите имя симуляции: ");
        if (name == null) return;
        simulation.setName(name);
        if (id == null) simulation.setId(1);
        else simulation.setId(id + 1);
        workWithExistingSimulation();
    }

    public static void workWithExistingSimulation() {
        int choice;
        while (true) {
            choice = menu.menuSimulation();
            switch (choice) {
                case 1:
                    adding();
                    break;
                case 2:
                    viewing();
                    break;
                case 3:
                    editing();
                    break;
                case 4:
                    deleting();
                    break;
                case 5:
                    Conditions condition = settingConditions();
                    forecast(condition);
                    break;
                case 6:
                    return;
            }
        }
    }

    public static void adding() {
        int choice;
        while (true) {
            choice = menu.AnimalsAndPlantsMenu();
            switch (choice) {
                case 1:
                    System.out.println("Возможные типы: " + Arrays.toString(animalTypes).replaceAll("[\\[\\],]", ""));
                    System.out.println("Введите тип: ");
                    String typeAnimal = validation.enterType(animalTypes);
                    System.out.println("Введите вид: ");
                    String speciesAnimal = validation.enterText();
                    System.out.println("Введите тип питания (если несколько, через пробел): ");
                    String diet = validation.enterText();
                    System.out.println("Введите количество: ");
                    int countAnimal = validation.getPositiveInt();
                    System.out.println("Далее введите необходимые ресурсы и климатические условия");
                    Conditions conditionAnimal = settingConditions();
                    simulation.getAnimals().add(new Animals(typeAnimal, speciesAnimal, diet, countAnimal, conditionAnimal));
                    break;
                case 2:
                    System.out.println("Возможные типы: " + Arrays.toString(plantTypes).replaceAll("[\\[\\],]", ""));
                    System.out.println("Введите тип: ");
                    String typePlant = validation.enterType(plantTypes);
                    System.out.println("Введите вид: ");
                    String speciesPlant = validation.enterText();
                    System.out.println("Введите количество: ");
                    int countPlant = validation.getPositiveInt();
                    System.out.println("Далее введите необходимые ресурсы и климатические условия");
                    Conditions conditionPlant = settingConditions();
                    simulation.getPlants().add(new Plants(typePlant, speciesPlant, countPlant, conditionPlant));
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void viewing() {
        int choice;
        while (true) {
            choice = menu.AnimalsAndPlantsMenu();
            switch (choice) {
                case 1:
                    viewingAnimals();
                    break;
                case 2:
                    viewingPlants();
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void editing() {
        int choice;
        int number;
        while (true) {
            choice = menu.AnimalsAndPlantsMenu();
            switch (choice) {
                case 1:
                    viewingAnimals();
                    System.out.println("Введите номер изменяемого животного: ");
                    number = validation.getPositiveInt();
                    if (number > simulation.getAnimals().size()) System.out.println("Такого животного нет");
                    else {
                        editingAnimals(number);
                    }
                    break;
                case 2:
                    viewingPlants();
                    System.out.println("Введите номер изменяемого растения: ");
                    number = validation.getPositiveInt();
                    if (number > simulation.getPlants().size()) System.out.println("Такого растения нет");
                    else {
                        editingPlants(number);
                    }
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void editingAnimals(int number) {
        int choice;
        while (true) {
            choice = menu.editingAnimalsMenu();
            switch (choice) {
                case 1:
                    System.out.println("Возможные типы: " + Arrays.toString(animalTypes).replaceAll("[\\[\\],]", ""));
                    System.out.println("Введите тип: ");
                    String type = validation.enterType(animalTypes);
                    simulation.getAnimals().get(number - 1).setType(type);
                    System.out.println("Тип успешно изменён");
                    break;
                case 2:
                    System.out.println("Введите вид: ");
                    String species = validation.enterText();
                    simulation.getAnimals().get(number - 1).setSpecies(species);
                    System.out.println("Вид успешно изменён");
                    break;
                case 3:
                    System.out.println("Введите тип питания: ");
                    String diet = validation.enterText();
                    simulation.getAnimals().get(number - 1).setDiet(diet);
                    System.out.println("Тип питания успешно изменён");
                    break;
                case 4:
                    System.out.println("Введите количество: ");
                    int count = validation.getPositiveInt();
                    simulation.getAnimals().get(number - 1).setCount(count);
                    System.out.println("Количество успешно изменено");
                    break;
                case 5:
                    editingConditions(simulation.getAnimals(), number);
                    break;
                case 6:
                    return;
            }
        }
    }

    public static void editingPlants(int number) {
        int choice;
        while (true) {
            choice = menu.editingPlantsMenu();
            switch (choice) {
                case 1:
                    System.out.println("Возможные типы: " + Arrays.toString(plantTypes).replaceAll("[\\[\\],]", ""));
                    System.out.println("Введите тип: ");
                    String type = validation.enterType(plantTypes);
                    simulation.getPlants().get(number - 1).setType(type);
                    System.out.println("Тип успешно изменён");
                    break;
                case 2:
                    System.out.println("Введите вид: ");
                    String species = validation.enterText();
                    simulation.getPlants().get(number - 1).setSpecies(species);
                    System.out.println("Вид успешно изменён");
                    break;
                case 3:
                    System.out.println("Введите количество: ");
                    int count = validation.getPositiveInt();
                    simulation.getPlants().get(number - 1).setCount(count);
                    System.out.println("Количество успешно изменено");
                    break;
                case 4:
                    editingConditions(simulation.getPlants(), number);
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void deleting() {
        int choice;
        int number;
        while (true) {
            choice = menu.AnimalsAndPlantsMenu();
            switch (choice) {
                case 1:
                    viewingAnimals();
                    System.out.println("Введите номер удаляемого животного: ");
                    number = validation.getPositiveInt();
                    if (number > simulation.getAnimals().size()) System.out.println("Такого животного нет");
                    else {
                        simulation.getAnimals().remove(number - 1);
                        System.out.println("Животное успешно удалено");
                    }
                    break;
                case 2:
                    viewingPlants();
                    System.out.println("Введите номер удаляемого растения: ");
                    number = validation.getPositiveInt();
                    if (number > simulation.getPlants().size()) System.out.println("Такого растения нет");
                    else {
                        simulation.getPlants().remove(number - 1);
                        System.out.println("Растение успешно удалено");
                    }
                    break;
                case 3:
                    return;
            }
        }
    }

    public static void viewingAnimals() {
        for (int i = 0; i < simulation.getAnimals().size(); i++) {
            System.out.println("--" + (i + 1) + ") Тип: " + simulation.getAnimals().get(i).getType() + " Вид: " + simulation.getAnimals().get(i).getSpecies()
                    + " Тип питания: " + simulation.getAnimals().get(i).getDiet() + " Количество: " + simulation.getAnimals().get(i).getCount() + "--");
        }
    }

    public static void viewingPlants() {
        for (int i = 0; i < simulation.getPlants().size(); i++) {
            System.out.println("--" + (i + 1) + ") Тип: " + simulation.getPlants().get(i).getType() + " Вид: " + simulation.getPlants().get(i).getSpecies()
            + " Количество: " + simulation.getPlants().get(i).getCount() + "--");
        }
    }

    public static Conditions settingConditions() {
        System.out.println("Введите среднюю температуру (°C): ");
        double temperature = validation.getRightCondition(0, 50);
        System.out.println("Введите среднюю влажность (%): ");
        double humidity = validation.getRightCondition(0, 100);
        System.out.println("Введите среднее количество воды (л): ");
        double waterAvailability = validation.getPositiveDouble();
        return new Conditions(temperature, humidity, waterAvailability);
    }

    public static void editingConditions(ArrayList<? extends Organism> beings, int number) {
        int choice;
        while (true) {
            choice = menu.conditionsMenu();
            switch (choice) {
                case 1:
                    System.out.println("Введите среднюю температуру (°C): ");
                    double temperature = validation.getRightCondition(0, 50);
                    beings.get(number - 1).getCondition().setAverageTemperature(temperature);
                    break;
                case 2:
                    System.out.println("Введите среднюю влажность (%): ");
                    double humidity = validation.getRightCondition(0, 100);
                    beings.get(number - 1).getCondition().setAverageHumidity(humidity);
                    break;
                case 3:
                    System.out.println("Введите среднее количество воды (л): ");
                    double waterAvailability = validation.getPositiveDouble();
                    beings.get(number - 1).getCondition().setWaterAvailability(waterAvailability);
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void forecastAnimals(Conditions condition) {
        if (!simulation.getAnimals().isEmpty()) {
            for (Animals animals : simulation.getAnimals()) {
                if (checkNormalConditions(condition, animals) && (animals.canEatAny(simulation.getAnimals()) || animals.canEatAny(simulation.getPlants()))) {
                    System.out.println("Популяция остаётся стабильной для " + animals.getType() + " : " + animals.getSpecies());
                } else if (checkGoodConditions(condition, animals) && (animals.canEatAny(simulation.getAnimals()) || animals.canEatAny(simulation.getPlants()))) {
                    System.out.println("Популяция увеличится для " + animals.getType() + " : " + animals.getSpecies());
                } else
                    System.out.println("Популяция уменьшится для " + animals.getType() + " : " + animals.getSpecies());
            }

        } else System.out.println("Животных нет");
    }

    public static void forecastPlants(Conditions condition) {
        if (!simulation.getPlants().isEmpty()) {
            for (Plants plants : simulation.getPlants()) {
                if (checkNormalConditions(condition, plants)) {
                    System.out.println("Популяция остаётся стабильной для " + plants.getType() + " : " + plants.getSpecies());
                } else if (checkGoodConditions(condition, plants)) {
                    System.out.println("Популяция увеличится для " + plants.getType() + " : " + plants.getSpecies());
                } else {
                    System.out.println("Популяция уменьшится для " + plants.getType() + " : " + plants.getSpecies());
                }
            }
        } else {
            System.out.println("Растений нет");
        }
    }

    public static void forecast(Conditions condition) {
        int choice;
        while (true) {
            choice = menu.AnimalsAndPlantsMenu();
            switch (choice) {
                case 1:
                    forecastAnimals(condition);
                    break;
                case 2:
                    forecastPlants(condition);
                    break;
                case 3:
                    return;
            }
        }
    }


    public static boolean checkGoodConditions(Conditions condition, Organism organism) {
        return (condition.getAverageTemperature() - organism.getCondition().getAverageTemperature() > 0 && condition.getAverageTemperature() - organism.getCondition().getAverageTemperature() < 7) &&
                (condition.getAverageHumidity() - organism.getCondition().getAverageHumidity() > 0 && condition.getAverageHumidity() - organism.getCondition().getAverageHumidity() < 20) &&
                condition.getWaterAvailability() - organism.getCondition().getWaterAvailability() > 0;
    }

    public static boolean checkNormalConditions(Conditions condition, Organism organism) {
        return condition.getAverageTemperature() == organism.getCondition().getAverageTemperature() &&
                condition.getAverageHumidity() == organism.getCondition().getAverageHumidity() &&
                condition.getWaterAvailability() == organism.getCondition().getWaterAvailability();
    }

    public static String getInputStringWithExit(String prompt) {
        System.out.println(prompt + "(или 'выход' для выхода): ");
        String input = validation.enterText();
        if (input.equalsIgnoreCase("выход")) {
            System.out.println("Выход из меню");
            return null;
        }
        return input;
    }

    public static void createFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}