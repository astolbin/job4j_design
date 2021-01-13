package file;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private final String source = "./data/server.log";
    private final String target = "./data/unavailable.csv";

    @Test
    public void whenHasUnavailable() {
        new Analizy().unavailable(source, target);
        List<String> rsl = getResult();

        assertThat(rsl.size(), is(2));
        assertThat(rsl.get(0), is("10:57:01;10:59:01"));
        assertThat(rsl.get(1), is("11:01:02;11:02:02"));
    }

    private List<String> getResult() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }
}