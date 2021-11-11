package determinateRandom;

public class DistributedRandomNumberGeneratorProbabilities {

    //Esta classe define a probabilidade de sair cada número
    //array 2 contem o valor da probabilidade
    //Quanto maior o valor, maior é a probabilidade sair
    private int[] array1 = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
    private int[] array2 = {4,4,3,2,2,2,2,2,3,3,3,3,4,4,5,5,5,5,5,5,4,4,4,4};
    int size_array1 = array1.length;
    int size_array2 = array2.length;
   /* public void debug (){
        System.out.println("Tamanho do array 1: " + size_array1);
        System.out.println("Tamanho do array 2: " + size_array2);
    }
*/

    private int[] array1_iluminação = {1,2,3,4};
    private int[] array2_iluminação = {10,15,2,2};

    public int[] getArray1() { return array1; }
    public int[] getArray2() { return array2; }
    public int[] getArray1_iluminação() { return array1_iluminação; }
    public int[] getArray2_iluminação() { return array2_iluminação; }
    public int prob(int[] arrayhora, int[] arrayprob){

        // maintain a frequency map to validate the results

        // make 1000000 calls to the random() function and
        // store results in a map
        //Generate
        RandomOperations dr = new RandomOperations();
        int val = dr.random(arrayhora, arrayprob);
        System.out.println("Valor random dos dois arrays de probabilidades:\t"+val);
        return val;
    }


}