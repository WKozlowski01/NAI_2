import java.util.ArrayList;
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
        List<Double> result = new ArrayList<>();

        for(int i = 0; i < input.size(); i++){
            double tmp = input.get(i) * weights.get(i);
            result.add(tmp);
        }
        double sum = 0;

        for(int i = 0; i < result.size(); i++){
            sum += result.get(i);
        }
         if( sum >= threshold ){
             return 1;
         }else return 0;
    }

}
