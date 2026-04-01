import java.util.*;

public class InvertedIndex {

    private Map<String, Map<String, Integer>> index = new HashMap<>();
    private Set<String> documents = new HashSet<>();

    public void add(String word, String document) {

        documents.add(document);

        index.putIfAbsent(word, new HashMap<>());
        Map<String, Integer> docMap = index.get(word);

        docMap.put(document, docMap.getOrDefault(document, 0) + 1);
    }

    public Map<String, Integer> get(String word) {
        return index.getOrDefault(word, new HashMap<>());
    }

    public int getDocumentCount() {
        return documents.size();
    }
}