package ar.edu.itba.eda;

public class StringUtils {
    private static int[] nextComputation(char[] query) {
        int[] next = new int[query.length];

        int border = 0;
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

    public static int indexOf(char[] query, char[] target) {
        if (query.length == 0) return 0;

        int[] next = nextComputation(query);
        int queryIndex = 0;

        for (int i = 0; i < target.length; i++) {
            while (queryIndex > 0 && query[queryIndex] != target[i]) {
                queryIndex = next[queryIndex - 1]; // retroceso
            }

            if (query[queryIndex] == target[i]) {
                queryIndex++;
            }

            if (queryIndex == query.length) {
                return i - query.length + 1; // posición de inicio
            }
        }

        return -1;
    }

    public static int indexOf(String query, String target) {
        return indexOf(query.toCharArray(), target.toCharArray());
    }

    public static boolean contains(char query, char[] array) {
        for (char myChar : array) {
            if (myChar == query) return true;
        }
        return false;
    }
}
