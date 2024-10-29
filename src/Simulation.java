import java.io.*;
import java.util.ArrayList;

public class Simulation {

    private String name;
    private ArrayList<Animals> animals = new ArrayList<>();
    private ArrayList<Plants> plants = new ArrayList<>();
    private int id;

    public Simulation(String name) {
        this.name = name;
    }

    public Simulation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Animals> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animals> animals) {
        this.animals = animals;
    }

    public ArrayList<Plants> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plants> plants) {
        this.plants = plants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAnimal(Animals animal) {
        animals.add(animal);
    }

    public void addPlant(Plants plant) {
        plants.add(plant);
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                (animals == null || animals.isEmpty()) &&
                (plants == null || plants.isEmpty()) &&
                (id <= 0);
    }

    public void saveSimulation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("simulations.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Simulation loadSimulation(String simulationName, int id) {
        File file = new File("simulations.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts[0].equalsIgnoreCase(simulationName) && parts[3].equalsIgnoreCase(String.valueOf(id))) {
                        Simulation simulation = new Simulation(parts[0]);
                        if (parts.length > 1 && !parts[1].isEmpty()) {
                            String[] animalsData = parts[1].split(",");
                            for (String animalData : animalsData) {
                                String[] animalParts = animalData.split(":");
                                if (animalParts.length == 4) {
                                    Animals animal = new Animals(animalParts[0], animalParts[1], animalParts[2], animalParts[3]);
                                    simulation.addAnimal(animal);
                                }
                            }
                        }
                        if (parts.length > 2 && !parts[2].isEmpty()) {
                            String[] plantsData = parts[2].split(",");
                            for (String plantData : plantsData) {
                                String[] plantParts = plantData.split(":");
                                if (plantParts.length == 3) {
                                    Plants plant = new Plants(plantParts[0], plantParts[1], plantParts[2]);
                                    simulation.addPlant(plant);
                                }
                            }
                        }
                        simulation.setId(Integer.parseInt(parts[3]));
                        return simulation;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Simulation();
    }

    public Integer getLastSimulationId() {
        File file = new File("simulations.txt");
        Integer lastId = null;

        try {
            if (!file.exists()) {
                return null;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length > 3) { // Убедитесь, что есть достаточно частей
                        lastId = Integer.parseInt(parts[3]); // Сохраняем id последней записи
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования id: " + e.getMessage());
        }
        return lastId; // Возвращаем id последней записи или null, если файл пуст
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(";");
        for (Animals animal : animals) {
            sb.append(animal.toString()).append(",");
        }
        sb.append(";");
        for (Plants plant : plants) {
            sb.append(plant.toString()).append(",");
        }
        sb.append(";");
        sb.append(id);
        return sb.toString();
    }

}
