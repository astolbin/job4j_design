package file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicates extends SimpleFileVisitor<Path> {
    private final Set<String> uniqueFiles = new HashSet<>();
    private final List<Path> duplicates = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileParams = file.toFile().getName() + file.toFile().length();
        if (uniqueFiles.contains(fileParams)) {
            duplicates.add(file);
        } else {
            uniqueFiles.add(fileParams);
        }
        return CONTINUE;
    }

    public List<Path> getDuplicates() {
        return duplicates;
    }
}
