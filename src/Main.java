import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

Scanner sc = new Scanner(System.in);
        Path testPath = Paths.get("src/iris_test.txt");
        Path treningPath = Paths.get("src/iris_training.txt");
        List<Vct> TreningVectrors = new ArrayList<>();
        List<Vct> TestVectrors = new ArrayList<>();


        try {
            Files.lines(treningPath).forEach(line -> {

                TreningVectrors.add(fileOperations(line));
            });

            Files.lines(testPath).forEach(line -> {

                TestVectrors.add(fileOperations(line));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

//        System.out.println("Wprowadź syganły wejściowe");
//        List<Double> inputs = new ArrayList<>();
//        for(int i =0; i <TreningVectrors.getFirst().getSize() ; i++){
//            inputs.add(sc.nextDouble());
//        }

//        System.out.println(inputs);

        Random rand  = new Random();
        List<Double> weightsRandom = Stream.generate(rand::nextDouble).limit(TreningVectrors.getFirst().getSize()).collect(Collectors.toList());
        double progRandom  = Math.random();



        Perceptron perceptron = new Perceptron(weightsRandom,progRandom);
//        System.out.println( perceptron.Compute(inputs));



        List<Integer> traningIfWork = new ArrayList<>();
        do  {
            traningIfWork.clear();
            for (Vct vct : TreningVectrors) {
                int dec = vct.getName().equals("Iris-setosa") ? 1 : 0;

                List<Double> wsp = new ArrayList<>();

                for (double x : vct.getCoordinates()) {
                    wsp.add(x);
                }
                int tmp = perceptron.Leran(wsp, dec);
                traningIfWork.add(tmp);
            }
        }while (!traningIfWork.contains(0));




        List<Integer> results = new ArrayList<>();


            for (Vct vct : TestVectrors) {

                int dec = vct.getName().equals("Iris-setosa") ? 1 : 0;
                int result = 0;
                List<Double> wsp = new ArrayList<>();

                for (double x : vct.getCoordinates()) {
                    wsp.add(x);
                }

                result = perceptron.Compute(wsp);

                if (result == dec) {
                    results.add(1);
                } else {
                    results.add(0);
                }
            }

        System.out.println(results);
        System.out.println("Skutecznosc wynosi: "+(results.stream().filter(n -> n==1).count() / results.size() ) * 100 +"%");


    }

    private static Vct fileOperations(String line) {
        String[] row = line.split("\t");

        for (int i = 0; i < row.length; i++) {
            row[i] = row[i].replace(',', '.');
        }

        String name = row[row.length - 1];

        Vct vct = new Vct(name, row.length - 1);
        vct.setCoordinates(
                Arrays.stream(row, 0, row.length - 1)
                        .mapToDouble(Double::parseDouble)
                        .toArray()
        );
        return vct;
    }
}
