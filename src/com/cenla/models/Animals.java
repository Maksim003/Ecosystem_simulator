package com.cenla.models;

import com.cenla.interfaces.Organism;

import java.util.*;

public class Animals implements Organism {
    private String type;
    private String species;
    private String diet;
    private int count;
    private Conditions condition;

    public Animals()  {
    }

    public Animals(String type, String species, String diet, int count, Conditions condition) {
        this.type = type;
        this.species = species;
        this.diet = diet;
        this.count = count;
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Conditions getCondition() {
        return condition;
    }

    public void setCondition(Conditions condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return type + ":" + species + ":" + diet + ":" + count + ":" + condition;
    }

    @Override
    public boolean eat(Organism organism) {
        //String[] diets = this.diet.split(" ");
        //for (String diet : diets) {
            if (this.diet.contains(organism.getType().toLowerCase()) || matchesWithEndings(this.diet, organism.getType())) {
                return true;
            }
        //}
        return false;
    }

    public boolean canEatAny(ArrayList<? extends Organism>  organisms) {
        for (Organism organism : organisms) {
            if (!organism.equals(this) && this.eat(organism)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchesWithEndings(String diet, String organismType) {
        Map<String, List<String>> endingsMap = new HashMap<>();
        endingsMap.put("млекопитающее", Arrays.asList("ое", "ие", "их"));
        endingsMap.put("птица", Arrays.asList("ца", "цы", "ц"));
        endingsMap.put("рептилия", Arrays.asList("ия", "ии", "ий"));
        endingsMap.put("амфибия", Arrays.asList("ия", "ии", "ий"));
        endingsMap.put("рыба", Arrays.asList("а", "ы", "б"));
        endingsMap.put("беспозвоночное", Arrays.asList("ое", "ые", "ых"));
        endingsMap.put("дерево", Arrays.asList("о", "ья"));
        endingsMap.put("кустарник", Arrays.asList("ик", "ки"));
        endingsMap.put("трава", Arrays.asList("а", "ы"));
        endingsMap.put("цветок", Arrays.asList("ок", "ки"));
        endingsMap.put("водное растение", Arrays.asList("ие", "ия"));

        if (endingsMap.containsKey(diet)) {
            for (String ending : endingsMap.get(diet)) {
                if (organismType.endsWith(ending)) {
                    return true;
                }
            }
        }

        return false;
    }
}
