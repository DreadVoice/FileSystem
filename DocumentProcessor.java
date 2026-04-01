import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DocumentProcessor {

    public static List<String> process(String filePath) {

        List<String> words = new ArrayList<>();

        try {
            String content = Files.readString(Paths.get(filePath));

            content = content.toLowerCase();

            String[] tokens = content.split("\\W+");

            for (String token : tokens) {
                if (!token.isEmpty()) {
                    words.add(token);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }

        return words;
    }
}