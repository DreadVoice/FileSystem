import java.util.*;

public class CLIEngine {

    private InvertedIndex index = new InvertedIndex();
    private boolean isIndexed = false;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equals("exit")) {
                System.out.println("Exiting...");
                break;
            }

            if (input.startsWith("index")) {
                String path = input.substring(5).trim();
                handleIndex(path);
            } 
            else if (input.startsWith("search")) {
                String query = input.substring(6).trim();
                handleSearch(query);
            } 
            else {
                System.out.println("Unknown command. Try: index, search, exit");
            }
        }
    }

    private void handleIndex(String path) {
        if (path.isEmpty()) {
            System.out.println("Usage: index <directory>");
            return;
        }

        List<String> files = FileScanner.scan(path);

        System.out.println("Indexing " + files.size() + " files...");

        for (String file : files) {
            List<String> words = DocumentProcessor.process(file);

            for (String word : words) {
                index.add(word, file);
            }
        }

        isIndexed = true;
        System.out.println("Indexing complete.");
    }

    private void handleSearch(String query) {
        if (!isIndexed) {
            System.out.println("Run 'index' first.");
            return;
        }

        System.out.println("Searching...");
        SearchEngine.search(query, index);
    }
}