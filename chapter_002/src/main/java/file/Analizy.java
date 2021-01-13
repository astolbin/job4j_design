package file;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        var unavailable = getUnavailable(getLines(source));
        saveResult(unavailable, target);
    }

    private List<String> getLines(String source) {
        List<String> rsl = new ArrayList<>();

        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(rsl::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rsl;
    }

    private List<String> getUnavailable(List<String> lines) {
        List<String> rsl = new ArrayList<>();
        boolean isUnavailable;
        String from = "";

        for (String line : lines) {
            String status = line.split(" ")[0];
            String time = line.split(" ")[1];

            isUnavailable = status.equals("400") || status.equals("500");

            if (isUnavailable && from.isEmpty()) {
                from = time;
            }

            if (status.equals("200") && !from.isEmpty()) {
                rsl.add(from + ";" + time);
                from = "";
            }
        }

        return rsl;
    }

    private void saveResult(List<String> unavailable, String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            unavailable.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Analizy().unavailable("server.log", "unavailable.csv");
    }
}