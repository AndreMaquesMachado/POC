package determinateRandom.numberOperations;



public class Specifications {
    public static Double truncateDecimal(double x, int numberofDecimals)
    {

        /*BigDecimal valor ;
        Double valor_double;
        if ( x > 0) {
            valor = new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR);
            valor_double = Double.valueOf( String.valueOf(valor));
            return valor_double;
        } else {
            valor = new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_CEILING);
            valor_double = Double.valueOf( String.valueOf(valor));
            return valor_double;
        }*/



        Double d =round(x, numberofDecimals);

        return d;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
