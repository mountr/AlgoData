public class afronden {
    private static double round(double input, int nrOfDigits) {
        return ((double) Math.round(input * Math.pow(10, nrOfDigits)))/Math.pow(10, nrOfDigits);
    }

    public static void main(String[] args) {
        System.out.println(round(103.43299,2));
    }

}
