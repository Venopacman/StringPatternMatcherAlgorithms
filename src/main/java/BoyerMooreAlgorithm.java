import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paul on 30.05.2016.
 */
public class BoyerMooreAlgorithm {
    HashMap<Integer, Integer> badCharacter;
    int[] goodSuff;
    long start;
    long end;

    public long matcher(String text, String str, String alphabet) {
        start = System.nanoTime();
        ArrayList<Integer> occurrences = new ArrayList<>();
        int n = text.length();
        int m = str.length();

        computeBChar(str,alphabet);
        computeGSuff(str);

        int i = 0;
        while (i < n - m + 1) {
            int j = m - 1;
            while (j >= 0 && str.charAt(j) == text.charAt(i + j))
                j--;
            if (j == -1) {
                occurrences.add(i);//Все вхождения
                i++;
            } else {
                i = i + Math.max(goodSuff[j], badCharacter.get((int) text.charAt(i + j)));
            }
        }
        end = System.nanoTime();
        return end - start;
    }

    public void computeBChar(String str,String alphabet) {
        //String alphabet = "abcde";
        badCharacter = new HashMap<>();
        for (int i = 0; i < alphabet.length(); i++) {
            badCharacter.put((int) alphabet.charAt(i), str.length());
        }
        for (int j = 0; j < str.length() - 1; j++) {
            badCharacter.put((int) str.charAt(j), str.length() - j - 1);
        }
    }

    public int[] computeGSuff(String substring) {
        int[] goodSuffix = new int[substring.length()];
        int[] suffixes = getSuffixes(substring);

        Arrays.fill(goodSuffix, substring.length());
        int pattern_shift = 0;
        for (int i = substring.length() - 1; i >= 0; --i)
            if (suffixes[i] == i + 1)
                while (pattern_shift < substring.length() - 1 - i) {
                    if (goodSuffix[pattern_shift] == substring.length())
                        goodSuffix[pattern_shift] = substring.length() - 1 - i;
                    pattern_shift++;
                }
        for (int i = 0; i <= substring.length() - 2; ++i)
            goodSuffix[substring.length() - 1 - suffixes[i]] = substring.length() - 1 - i;

        goodSuff = goodSuffix;
        return goodSuffix;
    }

    public int[] getSuffixes(String substring) {
        int[] indexes = new int[substring.length()];
        for (int i = 0; i < substring.length(); i++) {
            indexes[i] = getSuffixLength(substring, i);
        }
        return indexes;
    }

    private int getSuffixLength(String substring, int position) {
        int len = 0;
        int i = position;
        int j = substring.length() - 1;
        while (i >= 0 && substring.charAt(i) == substring.charAt(j)) {
            ++len;
            --i;
            --j;
        }
        return len;
    }
}

