package com.cenla.models;

import com.cenla.interfaces.Organism;

public class Plants implements Organism {
    private String type;
    private String species;
    private int count;
    private Conditions condition;

    public Plants() {
    }

    public Plants(String type, String species, int count, Conditions condition) {
        this.type = type;
        this.species = species;
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
        return type + ":" + species + ":" + count + ":" + condition;
    }

    @Override
    public boolean eat(Organism organism) {
        System.out.println("Растения не едят другие организмы.");
        return false;
    }


    /*@Override
    public boolean eat(com.cenla.models.Plants plant) {
        throw new UnsupportedOperationException("Растения не едят другие организмы.");
    }

    @Override
    public boolean eat(com.cenla.models.Animals animal) {
        throw new UnsupportedOperationException("Растения не едят другие организмы.");
    }*/
}
