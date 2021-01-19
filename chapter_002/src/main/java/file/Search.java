package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder or file extension is null.");
        }

        Path start = Paths.get(args[0]);
        String ext = args[1];
        search(start, ext).forEach(System.out::println);

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
