import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Perceptron {

    private List<Double> weights;
    private double threshold;
    private double learnConst = 0.1;

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

    public int Leran(List<Double> inputs, int decision) {
        int y = Compute(inputs);
        if (y == decision) return 1;

        double stalaPoprawki = (decision - y) * learnConst;


        IntStream.range(0, weights.size()).forEach(i -> weights.set(i, weights.get(i) + stalaPoprawki * inputs.get(i)));

        return 0;
    }

}
