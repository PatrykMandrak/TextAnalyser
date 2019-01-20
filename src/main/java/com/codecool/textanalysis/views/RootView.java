package com.codecool.textanalysis.views;

import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

public class RootView {

    private PrintStream output;

    public RootView(PrintStream printStream) {
        this.output = printStream;
    }

    public void displayNoFileProvidedMessage() {
        output.println("You need to provide file in console argument e.g. 'java Application filename.txt'");
    }

    public void displayFileName(String filename) {
        output.println("==" + filename + "==");
    }

    public void displayCharCount(int size) {
        output.println("Char count: " + size);
    }

    public void displayWordCount(int size) {
        output.println("Word count: " + size);
    }

    public void displayDictSize(int size) {
        output.println("Dict size: " + size);
    }

    public void displayMostUsedWords(Set<String> words) {
        output.println("Most used words (>1%): " + words);
    }

    public void displayLoveWordCount(int loveCount) {
        output.println("'love' count: " + loveCount);
    }

    public void displayHateWordCount(int hateCount) {
        output.println("'hate' count: " + hateCount);
    }

    public void displayMusicWordCount(int musicCount) {
        output.println("'music' count: " + musicCount);
    }

    public void displayVowelsPercentage(double percentage) {
        output.println(String.format("vowels %%: %.2f", percentage));
    }

    public void displayAToERatio(double ratio) {
        output.println(String.format("'a:e count ratio' : %.2f", ratio));
    }

    public void displayPercentageOfAllLetters(Map<String, Double> map) {
        map.forEach((key, value) -> output.print(String.format("[%s -> %.2f] ", key, value)));
        output.println();
    }

    public void displayBenchmarkTime(double time) {
        output.println(String.format("Benchmark time: %.2f secs", time));
    }
}