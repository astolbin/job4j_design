package file.find;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;

public class FileSearchFactory {
    public static FileSearch getSearcher(FindArgs args) {
        FileSearch rsl;

        switch (args.getType()) {
            case "name" -> rsl = new FileSearch(p -> p.getFileName().toString().equals(args.getName()));
            case "mask", "regexp" -> {
                PathMatcher pathMatcher =
                        FileSystems.getDefault().getPathMatcher("glob:**/" + args.getName());
                rsl = new FileSearch(pathMatcher::matches);
            }
            default -> throw new IllegalArgumentException("Search type does not match: [name, mask, regexp]");
        }

        return rsl;
    }
}
