package ar.edu.itba.eda;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class ApacheLevenshtein {
    private static final LevenshteinDistance levenshtein = new LevenshteinDistance();

    public static Integer calculate(String str1, String str2) {
        return levenshtein.apply(str1, str2);
    }
}