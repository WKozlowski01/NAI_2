import java.util.List;

public class Perceptron {

    private List<Double> weights;
    private double threshold;

    public Perceptron(List<Double> weights, double threshold) {
        this.weights = weights;
        this.threshold = threshold;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public int Compute(List<Double> input){
        return 0;
    }

    public void Learn(List<Double> inputs, int decision){

    }








}
