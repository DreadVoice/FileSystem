public class Ranker {

    public static double computeScore(String word, int frequency, InvertedIndex index) {

        int totalDocs = index.getDocumentCount();
        int docsWithWord = index.get(word).size();

        double idf = Math.log((double) totalDocs / (1 + docsWithWord));

        return frequency * idf;
    }
}