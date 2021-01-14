package file;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private final String source = "source.txt";
    private final String target = "target.txt";

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenHasUnavailable() throws IOException {
        List<String> log = List.of(
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "200 10:59:01",
                "500 11:01:02",
                "200 11:02:02",
                "400 11:15:01"
        );

        File source = folder.newFile(this.source);
        File target = folder.newFile(this.target);
        try (PrintWriter out = new PrintWriter(source)) {
            log.forEach(out::println);
        }

        new Analizy().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = getResult(target);

        assertThat(rsl.size(), is(2));
        assertThat(rsl.get(0), is("10:57:01;10:59:01"));
        assertThat(rsl.get(1), is("11:01:02;11:02:02"));
    }

    private List<String> getResult(File target) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }
}