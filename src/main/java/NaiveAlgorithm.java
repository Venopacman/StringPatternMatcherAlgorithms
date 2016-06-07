import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Paul on 29.05.2016.
 */
public class NaiveAlgorithm {
    long start;
    long end;

    long matcher(String text, String pattern) {
        //System.out.println("text:" + text + " pattern: " + pattern);
        //System.out.println("text:" + text.length() + " pattern: " + pattern.length());
        start = System.nanoTime();
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    //System.err.println("Проверка провалилась на символах: "+ text.substring(i + j, i + j + 1) +"    "+ pattern.substring(j, j + 1));
                    break;
                }
                if (j + 1 == pattern.length())
                    System.out.println("Подстрока входит со сдвигом: " + i);
            }
        }
        end = System.nanoTime();
        return end-start;
    }
}
