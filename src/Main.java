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
        Path testPath = Paths.get("iris_test.txt");
        Path treningPath = Paths.get("iris_training.txt");
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
        Random rand = new Random();
        List<Double> weightsRandom = Stream.generate(rand::nextDouble).limit(TreningVectrors.getFirst().getSize()).collect(Collectors.toList());
        double progRandom = Math.random();


        Perceptron perceptron = new Perceptron(weightsRandom, progRandom);

        List<Integer> traningIfWork = new ArrayList<>();
        do {
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
        } while (traningIfWork.contains(0));


        List<Integer> results = new ArrayList<>();


        for (Vct vct : TestVectrors) {

            int testDec = vct.getName().equals("Iris-setosa") ? 1 : 0;
            int result = 0;
            List<Double> wsp = new ArrayList<>();

            for (double x : vct.getCoordinates()) {
                wsp.add(x);
            }

            result = perceptron.Compute(wsp);

            if (result == testDec) {
                results.add(1);
            } else {
                results.add(0);
            }
        }

        System.out.println(results);
        double counter=0;

        for(var x : results){
            if (x == 1){
                counter++;
            }
        }
        System.out.println("Skutecznosc wynosi: " + (counter/(double)results.size())*100 +"%");

        Scanner userScanner = new Scanner(System.in);
        List<Double> userList = new ArrayList<>();
        while (true) {

            userList.clear();
            System.out.println("Podaj " + TreningVectrors.get(0).getSize() + " współrzędne wektora");
           for(int i =0 ; i< TreningVectrors.get(0).getSize();i++){
               userList.add(userScanner.nextDouble());
           }

            int result = perceptron.Compute(userList);
            String answer;
            if (result == 1){
                answer = "Iris-setosa";
            } else{
                answer = "Nie irys-setosa";
            }
            System.out.println("Ten kwiat to: "+answer+"\n");
        }
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