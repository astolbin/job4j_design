package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(".");
        search(start, "java").forEach(System.out::println);

        System.out.println(System.lineSeparator() + "Duplicates:");
        searchDuplicates(start).forEach(System.out::println);
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static List<Path> searchDuplicates(Path root) throws IOException {
        var searcher = new SearchDuplicates();
        Files.walkFileTree(root, searcher);
        return searcher.getDuplicates();
    }
}
