package file.find;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class FileSearch extends SimpleFileVisitor<Path> {
    private final Predicate<Path> filter;

    private final List<Path> files = new ArrayList<>();

    public FileSearch(Predicate<Path> predicate) {
        filter = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (filter.test(file)) {
            files.add(file);
        }

        return CONTINUE;
    }

    public List<Path> getFiles() {
        return files;
    }
}
