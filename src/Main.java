import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Validation validation = new Validation(sc);
    static Menu menu = new Menu(sc, validation);
    static File file = new File("simulations.txt");
    static Simulation simulation = new Simulation(file);

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
                    System.out.println("Введите тип: ");
                    String typeAnimal = validation.enterText();
                    System.out.println("Введите вид: ");
                    String speciesAnimal = validation.enterText();
                    System.out.println("Введите среду обитания: ");
                    String habitatAnimal = validation.enterText();
                    System.out.println("Введите тип питания: ");
                    String diet = validation.enterText();
                    simulation.getAnimals().add(new Animals(typeAnimal, speciesAnimal, habitatAnimal, diet));
                    break;
                case 2:
                    System.out.println("Введите тип: ");
                    String typePlant = validation.enterText();
                    System.out.println("Введите вид: ");
                    String speciesPlant = validation.enterText();
                    System.out.println("Условие роста: ");
                    String growthCondition = validation.enterText();
                    simulation.getPlants().add(new Plants(typePlant, speciesPlant, growthCondition));
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
                    System.out.println("Введите тип: ");
                    String type = validation.enterText();
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
                    System.out.println("Введите среду обитания: ");
                    String habitat = validation.enterText();
                    simulation.getAnimals().get(number - 1).setHabitat(habitat);
                    System.out.println("Среда обитания успешно изменена");
                    break;
                case 4:
                    System.out.println("Введите тип питания: ");
                    String diet = validation.enterText();
                    simulation.getAnimals().get(number - 1).setDiet(diet);
                    System.out.println("Тип питания успешно изменён");
                    break;
                case 5:
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
                    System.out.println("Введите тип: ");
                    String type = validation.enterText();
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
                    System.out.println("Введите условие роста: ");
                    String growthCondition = validation.enterText();
                    simulation.getPlants().get(number - 1).setGrowthCondition(growthCondition);
                    System.out.println("Условие роста успешно изменено");
                    break;
                case 4:
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
                    + " Среда обитания: " + simulation.getAnimals().get(i).getHabitat() + " Тип питания: " + simulation.getAnimals().get(i).getDiet() + "--");
        }
    }

    public static void viewingPlants() {
        for (int i = 0; i < simulation.getPlants().size(); i++) {
            System.out.println("--" + (i + 1) + ") Тип: " + simulation.getPlants().get(i).getType() + " Вид: " + simulation.getPlants().get(i).getSpecies()
                    + " Условие роста: " + simulation.getPlants().get(i).getGrowthCondition());
        }
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