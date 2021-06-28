package ru.job4j.template;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TextGeneratorTest {

    @Test
    public void whenGenerateSuccess() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of("name", "John", "subject", "you");
        String template = "I am a ${name}, Who are ${subject}?";
        String expected = "I am a John, Who are you?";
        assertEquals(expected, generator.produce(template, map));
    }

    @Test(expected = IllegalStateException.class)
    public void whenMapHasUnknownKey() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of(
                "name", "John",
                "subject", "you",
                "test", "test"
        );
        String template = "I am a ${name}, Who are ${subject}?";
        generator.produce(template, map);
    }

    @Test(expected = IllegalStateException.class)
    public void whenMapNotFoundKey() {
        Generator generator = new TextGenerator();
        Map<String, String> map = Map.of("name", "John");
        String template = "I am a ${name}, Who are ${subject}?";
        generator.produce(template, map);
    }
}