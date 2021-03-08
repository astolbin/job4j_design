package file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shell {
    private final static String SEPARATOR = "/";
    private final List<String> path = new ArrayList<>();

    public Shell() {
        path.add("");
    }

    public void cd(String path) {
        Arrays.stream(path.split(SEPARATOR)).forEach(this::to);
    }

    private void to(String dir) {
        if (dir.contains("..")) {
            if (this.path.size() > 1) {
                this.path.remove(this.path.size() - 1);
            }
        } else {
            this.path.add(dir);
        }
    }

    public String pwd() {
        return path.size() > 1 ? String.join(SEPARATOR, path) : SEPARATOR;
    }
}
