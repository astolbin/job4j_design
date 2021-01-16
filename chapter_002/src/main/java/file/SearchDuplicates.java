package file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicates implements FileVisitor<Path> {
    private final Map<String, List<Path>> duplicates = new HashMap<>();

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<Path> paths;
        String key = file.toFile().getName() + file.toFile().length();
        if (duplicates.containsKey(key)) {
            paths = duplicates.get(key);
        } else {
            paths = new ArrayList<>();
            duplicates.put(key, paths);
        }
        paths.add(file);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }

    public List<Path> getDuplicates() {
        return duplicates.values().stream()
                .filter(files -> files.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
