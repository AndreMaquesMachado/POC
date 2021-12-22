package determinateRandom;

import java.util.Random;

public class RandomOperations {

    public static int rand ( int min, int max){
        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }

        return new Random().nextInt(max - min + 1) + min;
    }

    // Function to generate random input from an list according to
    // given probabilities
    public static int random ( int[] input, int[] probability){
        int n = input.length;
        if (n != probability.length) {
            return -1;        // error
        }

        // construct an sum list from given probabilities
        int[] prob_sum = new int[n];

        // prob_sum[i] holds sum of all probability[j] for 0 <= j <=i
        prob_sum[0] = probability[0];
        for (int i = 1; i < n; i++) {
            prob_sum[i] = prob_sum[i - 1] + probability[i];
        }

        // generate a random integer from 1 to 100
        // and check where it lies in prob_sum[]
        int r = rand(1, 100);

        // based on the comparison result, return corresponding
        // element from the input list

        if (r <= prob_sum[0]) {  // handle 0'th index separately
            return input[0];
        }

        for (int i = 1; i < n; i++) {
            if (r > prob_sum[i - 1] && r <= prob_sum[i]) {
                return input[i];
            }
        }
        return -1;
    }



    //Este metodo serve para definir valores de 0 a n de forma a tomar decisoes
    public int decisao(int n) {
        Random randec = new Random();
        int resultado = randec.nextInt(n);
        return resultado;
    }
    //Random entre dois Valores
    // low é o limite da hora
    public int horaentrevalores(int low, int high) {
        Random r = new Random();
        int result = r.nextInt((high+1) - low) + low;
        return result;
    }
    public double horaentrevalores_double(double low, double high) {
        Random r = new Random();
        double randomValue = r.nextDouble() *(high-low)+ low;
        return randomValue;
    }

}

