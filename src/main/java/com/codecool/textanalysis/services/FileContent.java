package com.codecool.textanalysis.services;

import java.util.Iterator;

public class FileContent implements IterableText {

    private String filename;

    public FileContent(String filename) {
        this.filename = filename;
    }

    public Iterator<String> charIterator() {
        return new CharIterator(this);
    }

    public Iterator<String> wordIterator() {
        return new WordIterator(this);
    }

    public String getFilename() {
        return filename;
    }
}