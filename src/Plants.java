public class Plants {
    private String type;
    private String species;
    private String growthCondition;

    public Plants() {
    }

    public Plants(String type, String species, String growthCondition) {
        this.type = type;
        this.species = species;
        this.growthCondition = growthCondition;
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

    public String getGrowthCondition() {
        return growthCondition;
    }

    public void setGrowthCondition(String growthCondition) {
        this.growthCondition = growthCondition;
    }

    @Override
    public String toString() {
        return type + ":" + species + ":" + growthCondition; // Формат для сохранения
    }

}
