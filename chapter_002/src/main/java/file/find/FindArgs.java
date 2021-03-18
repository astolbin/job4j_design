package file.find;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindArgs {
    private final Map<String, String> values = new HashMap<>();
    private static final String LS = System.lineSeparator();

    private String get(String key) {
        if (!values.containsKey(key)) {
            String message = "Argument '" + key + "' not found" + LS
                    + "-d - directory to search files" + LS
                    + "-n - file name, mask or regexp" + LS
                    + "-t - search type (name, mask, regexp)" + LS
                    + "-o - output file";
            throw new IllegalArgumentException(message);
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(param -> param.split("="))
                .forEach(param -> {
                    checkFormat(param);
                    values.put(param[0].substring(1), param[1]);
                });
    }

    private void checkFormat(String[] param) {
        if (param.length != 2 || !param[0].startsWith("-")) {
            throw new IllegalArgumentException("Arguments must be in '-key=value' format");
        }
    }

    public String getName() {
        return get("n");
    }

    public String getType() {
        return get("t");
    }

    public String getDir() {
        return get("d");
    }

    public String getOutput() {
        return get("o");
    }

    public static FindArgs of(String[] args) {
        FindArgs names = new FindArgs();
        names.parse(args);
        names.validate();
        return names;
    }

    private void validate() {
        getDir();
        getOutput();
        getType();
        getName();
    }
}
