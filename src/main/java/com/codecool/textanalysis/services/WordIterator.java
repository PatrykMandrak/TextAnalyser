package com.codecool.textanalysis.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class WordIterator implements Iterator<String> {

    private FileContent fileContent;
    private List<String> words;
    private int index;

    WordIterator(FileContent fileContent) {
        this.fileContent = fileContent;
        this.words = getWordsFromFile();
    }

    public boolean hasNext() {
        return index < words.size();
    }

    public String next() {
        return this.hasNext() ? words.get(index++) : null;
    }

    private List<String> getWordsFromFile() {
        String fileContent = getFileContent(this.fileContent.getFilename());
        String[] words = fileContent.trim().split("\\s+");
        return Arrays.stream(words).map(String::toLowerCase).collect(Collectors.toList());
    }

    private String getFileContent(String filename) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public int getWordsQuantity() {
        return words.size();
    }
}