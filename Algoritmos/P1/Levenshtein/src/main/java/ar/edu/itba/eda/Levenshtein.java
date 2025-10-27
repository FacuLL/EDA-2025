package ar.edu.itba.eda;

public class Levenshtein {

    public static int min(int a, int... rest) {
        if (rest.length == 0) return a;
        int min = a;
        for (int number : rest) {
            min = Math.min(min, number);
        }
        return min;
    }

    public static int calculate(String s1, String s2) {
        int[][] distances = new int[s1.length()+1][s2.length()+1];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                if (i == 0) {
                    if (j == 0) distances[i][j] = 0;
                    else distances[i][j] = distances[i][j-1] + 1;
                } else {
                    if (j == 0) distances[i][j] = distances[i-1][j] + 1;
                    else {
                        distances[i][j] = min(
                          distances[i-1][j-1] + (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1),
                          distances[i-1][j] + 1,
                          distances[i][j-1] + 1
                        );
                    }
                }
            }
        }
        return distances[distances.length-1][distances[0].length-1];
    }
}