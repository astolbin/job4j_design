package file.find;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;

public class Finder {
    public static void main(String[] args) {
        try {
            new Finder().find(args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void find(String[] args) throws Exception {
        FindArgs findArgs = FindArgs.of(args);
        Path start = Paths.get(findArgs.getDir());
        FileSearch searcher = FileSearchFactory.getSearcher(findArgs);
        Files.walkFileTree(start, searcher);
        log((searcher.getFiles()), findArgs.getOutput());
    }

    private void log(List<Path> files, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            files.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
