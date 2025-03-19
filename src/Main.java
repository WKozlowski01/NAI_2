import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Wprowadź dwie wagi");
        List<Double> weights = new ArrayList<Double>();


        for(int i =0;i < 2; i++ ) {
            weights.add(sc.nextDouble());
        }

        System.out.println("Wprowadź próg");
        double prog = sc.nextDouble();


        System.out.println("Wprowadź dwa syganły wejściowe");
        List<Double> inputs = new ArrayList<>();
        for(int i =0; i< 2; i++){
            inputs.add(sc.nextDouble());
        }

        System.out.println("Wprowadź oczekiwaną odpowiedź");
        int answer = sc.nextInt();

        Perceptron perceptron = new Perceptron(weights,prog);



    }
}
