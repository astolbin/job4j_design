package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String text;
        try {
            text = Files.readString(Paths.get(cachingDir + "/" + key));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return text;
    }
}