import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileScanner {

    public static List<String> scan(String directory) {

        List<String> fileList = new ArrayList<>();

        try {
            Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .forEach(path -> fileList.add(path.toString()));

        } catch (IOException e) {
            System.out.println("Error reading directory.");
        }

        return fileList;
    }
}