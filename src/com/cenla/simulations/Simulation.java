package com.cenla.simulations;

import com.cenla.interfaces.Organism;
import com.cenla.models.Animals;
import com.cenla.models.Conditions;
import com.cenla.models.Plants;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {

    private String name;
    private ArrayList<Animals> animals = new ArrayList<>();
    private ArrayList<Plants> plants = new ArrayList<>();
    private int id;
    File file = new File("simulations.txt");
    private static final Random random = new Random();

    public Simulation(String name) {
        this.name = name;
    }

    public Simulation(File file) {
        this.file = file;
        //executorService= Executors.newSingleThreadExecutor();
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

    public static Conditions generateRandomConditions() {
        double temperature = Math.round(random.nextDouble() * 50 * 10.0) / 10.0;
        double humidity = Math.round(random.nextDouble() * 100 * 10.0) / 10.0;
        double waterAvailability = Math.round(random.nextDouble() * 1000 * 10.0) / 10.0;
        return new Conditions(temperature, humidity, waterAvailability);
    }

    public synchronized void update() {
        Conditions condition = generateRandomConditions();
        simulateEating();
        logChanges(condition);
        System.out.println("Изменение условий");
    }

    private void logChanges(Conditions condition) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("simulation_log.txt", true))) {
            writer.write(condition.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
                (animals == null || animals.isEmpty()) &&
                (plants == null || plants.isEmpty()) &&
                (id <= 0);
    }

    public void saveSimulation() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(this.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Simulation loadSimulation(String simulationName, int id) {
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
                            if (animalParts.length == 7) {
                                Conditions condition = new Conditions(Double.parseDouble(animalParts[4]), Double.parseDouble(animalParts[5]), Double.parseDouble(animalParts[6]));
                                Animals animal = new Animals(animalParts[0], animalParts[1], animalParts[2], Integer.parseInt(animalParts[3]), condition);
                                simulation.addAnimal(animal);
                            }
                        }
                    }
                    if (parts.length > 2 && !parts[2].isEmpty()) {
                        String[] plantsData = parts[2].split(",");
                        for (String plantData : plantsData) {
                            String[] plantParts = plantData.split(":");
                            if (plantParts.length == 6) {
                                Conditions condition = new Conditions(Double.parseDouble(plantParts[3]), Double.parseDouble(plantParts[4]), Double.parseDouble(plantParts[5]));
                                Plants plant = new Plants(plantParts[0], plantParts[1], Integer.parseInt(plantParts[2]), condition);
                                simulation.addPlant(plant);
                            }
                        }
                    }
                    simulation.setId(Integer.parseInt(parts[3]));
                    return simulation;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Simulation(file);
    }

    public void removeAnimal(Animals animal) {
        if (animals.contains(animal)) {
            System.out.println("съели животное");
            if (animal.getCount() > 1) animal.setCount(animal.getCount() - 1);
            else animals.remove(animal);
        }
    }

    public void removePlant(Plants plant) {
        if (plants.contains(plant)) {
            System.out.println("съели растение");
            if (plant.getCount() > 1) plant.setCount(plant.getCount() - 1);
            else plants.remove(plant);
        }
    }

    public void simulateEating() {
        if (animals.isEmpty()) return;
        Animals predator = animals.get(random.nextInt(animals.size()));
        if (random.nextBoolean()) {
            if (animals.size() > 1) {
                Animals preyAnimal = animals.get(random.nextInt(animals.size()));
                while (preyAnimal == predator) {
                    preyAnimal = animals.get(random.nextInt(animals.size()));
                }
                animalEats(predator, preyAnimal);
            }
        } else {
            if (!plants.isEmpty()) {
                Plants preyPlant = plants.get(random.nextInt(plants.size()));
                animalEats(predator, preyPlant);
            }
        }
    }

    public void animalEats(Animals predator, Organism prey) {
        if (predator.eat(prey)) {
            if (prey instanceof Animals) {
                removeAnimal((Animals) prey);
            } else if (prey instanceof Plants) {
                removePlant((Plants) prey);
            }
        }
    }

    public Integer getLastSimulationId() {
        Integer lastId = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 3) {
                    lastId = Integer.parseInt(parts[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Ошибка преобразования id: " + e.getMessage());
        }
        return lastId;
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
