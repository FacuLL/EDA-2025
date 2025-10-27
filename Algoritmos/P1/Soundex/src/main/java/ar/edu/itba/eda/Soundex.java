package ar.edu.itba.eda;

public class Soundex {

    public static float comparate(String s1, String s2) {
        if (s1.length() != 4 || s2.length() != 4) throw new IllegalArgumentException();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        float result = 0;
        for (int i = 0; i < 4; i++) {
            if (c1[i] == c2[i]) result += 0.25f;
        }
        return result;
    }

    public static String encode(String word) {
        word = word.toUpperCase();
        char[] in = word.toCharArray();
        char[] out = {'0', '0', '0', '0'};
        int count = 0;
        if (in.length > 0) out[count++] = in[0];
        int last = weight(in[0]);
        for (int i = 1; i < in.length && count < 4; i++) {
            char current = weight(in[i]);
            if (current != '0' && current != last) {
                out[count++] = current;
            }
            last = current;
        }
        return new String(out);
    }

    public static char weight(char letter) {
        // TODO: Implementar mapping
        return switch (letter) {
            case 'B', 'F', 'P', 'V' -> '1';
            case 'C', 'G', 'J', 'K', 'Q', 'S', 'X', 'Z' -> '2';
            case 'D', 'T' -> '3';
            case 'L' -> '4';
            case 'M', 'N' -> '5';
            case 'R' -> '6';
            default -> '0';
        };
    }
}