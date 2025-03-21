import java.util.Arrays;

public class Vct {

    private String name;
    private int size;
    private double coordinates[];


    public Vct(String name, int size) {
        this.name = name;
        this.size = size;
        coordinates = new double[size];
    }


    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return Arrays.toString(coordinates) + " " + name;
    }
}
