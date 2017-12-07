package pz4.task1;

/**
 * Created by babagay on 29.11.15.
 */
public class Auto {

    private String color;
    private String power;
    private String name;

    public Auto(String name, String color, String power) {
        this.name = name;
        this.color = color;
        this.power = power;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
