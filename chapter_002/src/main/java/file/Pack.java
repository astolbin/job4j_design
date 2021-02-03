package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Pack {
    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);

        if (argZip.valid()) {
            Zip zip = new Zip();
            zip.packFiles(getFiles(argZip), Paths.get(argZip.output()));
        }
    }

    private static List<Path> getFiles(ArgZip argZip) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> {
            return !p.toFile().getName().endsWith(argZip.exclude());
        });
        Files.walkFileTree(Paths.get(argZip.directory()), searcher);
        return searcher.getPaths();
    }
}
