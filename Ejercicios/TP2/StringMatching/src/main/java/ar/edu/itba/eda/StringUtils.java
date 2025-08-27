package ar.edu.itba.eda;

public class StringUtils {
    // Metodo fuerza bruta
//    private static boolean search(char[] query, char[] target, int index) {
//        if (target.length - index < query.length) return false;
//        for (int i = 0; i < query.length; i++) {
//            if (target[index + i] != query[i]) return false;
//        }
//        return true;
//    }
//
//    public static int indexOf(char[] query, char[] target) {
//        for (int i = 0; i < target.length; i++) {
//            if (search(query, target, i)) return i;
//        }
//        return -1;
//    }

    private static int[] nextComputation(char[] query) {
        int[] next = new int[query.length];

        int border = 0;  // Length of the current border
        int rec = 1;


        while (rec < query.length) {
            if (query[rec] == query[border]) {
                border++;
                next[rec] = border;
                rec++;
            } else {
                if (border != 0) {
                    border = next[border - 1];
                } else {
                    next[rec] = 0;
                    rec++;
                }
            }
        }
        return next;
    }


}