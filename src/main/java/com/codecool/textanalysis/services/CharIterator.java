package com.codecool.textanalysis.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharIterator implements Iterator<String> {

    private FileContent fileContent;
    private List<String> chars;
    private int index;

    CharIterator(FileContent fileContent) {
        this.fileContent = fileContent;
        this.chars = getCharsFromFile();
    }

    @Override
    public boolean hasNext() {
        return index < chars.size();
    }

    @Override
    public String next() {
        return this.hasNext() ? chars.get(index++) : null;
    }

    private List<String> getCharsFromFile() {
        String fileContent = getFileContent(this.fileContent.getFilename());

        List<String> result = new ArrayList<>();
        for (char ch : fileContent.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.add(Character.toString(ch).toLowerCase());
            }
        }
        return result;
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
}