public class Conditions {
    private int temperature;
    private int humidity;
    private int amountOfWater;

    public Conditions() {
    }

    public Conditions(int temperature, int humidity, int amountOfWater) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.amountOfWater = amountOfWater;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getAmountOfWater() {
        return amountOfWater;
    }

    public void setAmountOfWater(int amountOfWater) {
        this.amountOfWater = amountOfWater;
    }
}
