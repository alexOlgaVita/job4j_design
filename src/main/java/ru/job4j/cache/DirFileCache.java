package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        try (BufferedReader input = new BufferedReader(new FileReader(cachingDir+key))) {
            put(key,input.lines().collect(Collectors.joining(System.lineSeparator())));
            return get(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
