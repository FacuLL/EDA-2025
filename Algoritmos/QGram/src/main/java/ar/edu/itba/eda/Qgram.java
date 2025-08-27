package ar.edu.itba.eda;

import java.util.HashMap;
import java.util.Map;

public class Qgram {
    private final int order;

    public Qgram(int order) {
        this.order = order;
    }

    private String completeWord(String word) {
        StringBuilder wordBuilder = new StringBuilder(word);
        for (int i = 0; i < order - 1 ; i++) {
            wordBuilder = new StringBuilder("#" + wordBuilder + "#");
        }
        return wordBuilder.toString();
    }

    private Map<String, Integer> getTokens(String word) {
        word = completeWord(word);
        char[] array = word.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length - order; i++) {
            StringBuilder token = new StringBuilder();
            for (int j = 0; j < order; j++) {
                token.append(array[i+j]);
            }
            map.putIfAbsent(token.toString(), 0);
            map.put(token.toString(), map.get(token.toString()) + 1);
        }
        return map;
    }

    public void printTokens(String word) {
        Map<String, Integer> tokens = getTokens(word);
        for (Map.Entry<String, Integer> entry : tokens.entrySet()) {
            System.out.printf("%s %d%n", entry.getKey(), entry.getValue());
        }
    }

    public void calculateDistance(String word1, String word2) {
        Map<String, Integer> tokens1 = getTokens(word1);
        Map<String, Integer> tokens2 = getTokens(word2);
        Integer total = tokens1.size() + tokens2.size();
        Integer notShared = 0;

    }


}