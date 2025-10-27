package ar.edu.itba.eda;

import java.util.HashMap;
import java.util.Map;

public class Qgram {
    private final int order;

    public Qgram(int order) {
        this.order = order;
    }

    public String completeWord(String word) {
        StringBuilder wordBuilder = new StringBuilder(word);
        for (int i = 0; i < order - 1 ; i++) {
            wordBuilder = new StringBuilder("#" + wordBuilder + "#");
        }
        return wordBuilder.toString();
    }

    public Map<String, Integer> getTokens(String word) {
        word = completeWord(word);
        char[] array = word.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length - order + 1; i++) {
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

    public double calculateDistance(String word1, String word2) {
        Map<String, Integer> tokens1 = getTokens(word1);
        Map<String, Integer> tokens2 = getTokens(word2);
        Integer total1 = tokens1.values().stream().reduce(Integer::sum).orElse(0);
        Integer total2 = tokens2.values().stream().reduce(Integer::sum).orElse(0);
        tokens2.forEach((token, occurrences) -> {
            Integer actualOccurrences = tokens1.getOrDefault(token, 0);
            tokens1.put(token, Math.abs(actualOccurrences - occurrences));
        });
        Integer notSharedCount = tokens1.values().stream().reduce(Integer::sum).orElse(0);
        if (total1+total2 == 0) return 0.0;
        return (double) (total1 + total2 - notSharedCount) / (total1 + total2);
    }


}