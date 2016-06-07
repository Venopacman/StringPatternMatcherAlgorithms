import java.util.Random;

/**
 * Created by Paul on 29.05.2016.
 */
public class MainCalculation {
    static Random rand = new Random();

    public static void main(String[] args) {
        /*Constraints and i.e.*/
        final int n = 30;
        final int m = n / 3;
        final int amountOfComputations = 100;
        long naiveTime = 0;
        long rabinTime = 0;
        long kmpTime = 0;
        long boyerTime = 0;
        String alphabet = "abcde";
        String worstCaseAlpabet = "a";


        /*algorithms work and time computation*/
        NaiveAlgorithm naiveAlgorithm = new NaiveAlgorithm();
        RabinKarpAlgorithm rabinKarpAlgorithm = new RabinKarpAlgorithm();
        KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();
        BoyerMooreAlgorithm boyerMooreAlgorithm = new BoyerMooreAlgorithm();
        for (int i = 0; i < amountOfComputations; i++) {
            String textString = getRandomString(alphabet, n);
            String patternString = getRandomString(alphabet, m);
            naiveTime += naiveAlgorithm.matcher(textString, patternString);
            rabinTime += rabinKarpAlgorithm.matcher(textString, patternString, alphabet.length(), 11);
            boyerTime += boyerMooreAlgorithm.matcher(textString, patternString, alphabet);
            kmpTime += kmpAlgorithm.matcher(textString, patternString);
        }
        naiveTime = naiveTime / amountOfComputations;
        rabinTime = rabinTime / amountOfComputations;
        boyerTime = boyerTime / amountOfComputations;
        kmpTime = kmpTime / amountOfComputations;
        System.out.println("Naive time of work: " + naiveTime);
        System.out.println("Rabin-Karp time of work: " + rabinTime);
        System.out.println("Boyer-Moore time of work: " + boyerTime);
        System.out.println("KMP time of work: " + kmpTime);

    }

    /* method for generating random string from some alphabet*/
    public static String getRandomString(String alphabet, int length) {
        String strToReturn = "";
        for (int i = 0; i < length; i++) {
            strToReturn += alphabet.charAt(randInt(0, alphabet.length() - 1) % alphabet.length());
        }

        return strToReturn;
    }

    private static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
}
