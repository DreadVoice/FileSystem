import java.util.*;

public class SearchEngine {

    public static void search(String query, InvertedIndex index) {

        String[] words = query.toLowerCase().split("\\W+");

        Map<String, Double> scores = new HashMap<>();

        for (String word : words) {

            Map<String, Integer> postings = index.get(word);

            for (String doc : postings.keySet()) {

                int freq = postings.get(doc);

                double score = Ranker.computeScore(word, freq, index);

                scores.put(doc, scores.getOrDefault(doc, 0.0) + score);
            }
        }

        if (scores.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        List<Map.Entry<String, Double>> resultList = new ArrayList<>(scores.entrySet());

        resultList.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        int count = 1;
        for (Map.Entry<String, Double> entry : resultList) {
            System.out.println(count + ". " + entry.getKey() + " (score: " + entry.getValue() + ")");
            count++;

            if (count > 10) break;
        }
    }
}