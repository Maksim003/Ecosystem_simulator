public class Animals {
    private String type;
    private String species;
    private String habitat;
    private String diet;

    public Animals() {
    }

    public Animals(String type, String species, String diet, String habitat) {
        this.type = type;
        this.species = species;
        this.diet = diet;
        this.habitat = habitat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
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

    @Override
    public String toString() {
        return type + ":" + species + ":" + habitat + ":" + diet; // Формат для сохранения
    }

   /* @Override
    public String toString() {
        return  type + '\'' +
                "," + species +
                "," + habitat +
                "," + diet +
                "," + age +
                "," + lifespan;
    }*/
}
