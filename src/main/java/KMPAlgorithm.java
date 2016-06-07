import java.util.ArrayList;

/**
 * Created by Paul on 30.05.2016.
 */
public class KMPAlgorithm {
    long start;
    long end;

    long matcher(String text, String pattern) {
        start = System.nanoTime();
        int Pi[] = computePrefixFunction(pattern);
        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i))
                q = Pi[q-1];
            if (pattern.charAt(q) == text.charAt(i))
                q ++;
            if (q == pattern.length()) {
                System.out.println("Подстрока входит со сдвигом: " + (i - pattern.length()));
                q = Pi[q-1];
            }
        }

        end = System.nanoTime();
        return end - start;
    }

    private int[] computePrefixFunction(String pattern) {
        int Pi[] = new int[pattern.length()];
        Pi[0] = 0;
        int k = 0;
        for (int q = 1; q < pattern.length(); q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q))
                k = Pi[k-1];
            if (pattern.charAt(k) == pattern.charAt(q))
                k = k + 1;
            Pi[q] = k;
        }
        return Pi;
    }
}
