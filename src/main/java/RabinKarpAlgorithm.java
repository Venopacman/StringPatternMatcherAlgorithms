/**
 * Created by Paul on 29.05.2016.
 */
public class RabinKarpAlgorithm {
    long start;
    long end;

    long matcher(String text, String pattern, int dimension, int module) {
        int h = (int) Math.pow(dimension, (pattern.length() - 1)) % module;
        int p = 0;
        int t = 0;

        for (int i = 1; i < pattern.length(); i++) {
            p += (dimension * p + pattern.charAt(i)) % module;
            t += (dimension * t + text.charAt(i)) % module;
        }
        start = System.nanoTime();
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (p == t) {
                for (int j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        //System.err.println("Проверка провалилась на символах: " + text.substring(i + j, i + j + 1) + "    " + pattern.substring(j, j + 1));
                        break;
                    }
                    if (j + 1 == pattern.length())
                        System.out.println("Подстрока входит со сдвигом: " + i);
                }
            }
            if (i < text.length() - pattern.length()) {
                t = (dimension * (t - text.charAt(i + 1) * h) + text.charAt(i + pattern.length())) % module;
            }
        }
        end = System.nanoTime();
        return end - start;
    }
}
