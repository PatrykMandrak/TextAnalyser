package com.codecool.textanalysis.controllers;

import com.codecool.textanalysis.services.FileContent;
import com.codecool.textanalysis.services.IterableText;
import com.codecool.textanalysis.views.RootView;
import com.codecool.textanalysis.services.StatisticalAnalysis;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RootController {

    private String[] consoleArgs;
    private RootView rootView;
    private long startingTime;

    public RootController (String[] consoleArgs, RootView rootView) {
        this.consoleArgs = consoleArgs;
        this.rootView = rootView;
    }

    public void start() {
        if (this.consoleArgs.length == 0) {
            rootView.displayNoFileProvidedMessage();
            return;
        }
        this.startingTime = System.nanoTime();
        for (String arg : this.consoleArgs) {
            checkIfFileExists(arg);
            IterableText iterableText = new FileContent(arg);
            handleAnalysisAndDisplayResults(arg, iterableText);
        }
        showBenchmark();
    }

    private void checkIfFileExists(String filepath) {
        File f = new File(filepath);
        if (!(f.exists() && !f.isDirectory())) {
            throw new IllegalArgumentException(filepath + " file does not exist!");
        }
    }

    private void handleAnalysisAndDisplayResults(String filename, IterableText iterableText) {
        showFileName(filename);
        showAllCharactersCountInFile(iterableText);
        showAllWordsCountInFile(iterableText);
        showDifferentWordsUsedQuantity(iterableText);
        showMostUsedWords(iterableText);
        showLoveCount(iterableText);
        showHateCount(iterableText);
        showMusicCount(iterableText);
        showVowelsPercentage(iterableText);
        showAToERatio(iterableText);
        showPercentageOfAllLetters(iterableText);
    }

    private void showFileName(String filename) {
        rootView.displayFileName(filename);
    }

    private void showAllCharactersCountInFile(IterableText iterableText) {
        rootView.displayCharCount(new StatisticalAnalysis(iterableText.charIterator()).size());
    }

    private void showAllWordsCountInFile(IterableText iterableText) {
        rootView.displayWordCount(new StatisticalAnalysis(iterableText.wordIterator()).size());
    }

    private void showDifferentWordsUsedQuantity(IterableText iterableText) {
        rootView.displayDictSize(new StatisticalAnalysis(iterableText.wordIterator()).dictionarySize());
    }

    private void showMostUsedWords(IterableText iterableText) {
        double onePercent = 0.01;
        Double onePercentOfAllWords = onePercent * new StatisticalAnalysis(iterableText.wordIterator()).size();
        rootView.displayMostUsedWords(new StatisticalAnalysis(iterableText.wordIterator())
                .occurMoreThan(onePercentOfAllWords.intValue()));
    }

    private void showLoveCount(IterableText iterableText) {
        rootView.displayLoveWordCount(new StatisticalAnalysis(iterableText.wordIterator()).countOf("love"));
    }

    private void showHateCount(IterableText iterableText) {
        rootView.displayHateWordCount(new StatisticalAnalysis(iterableText.wordIterator()).countOf("hate"));
    }

    private void showMusicCount(IterableText iterableText) {
        rootView.displayMusicWordCount(new StatisticalAnalysis(iterableText.wordIterator()).countOf("music"));
    }

    private void showVowelsPercentage(IterableText iterableText) {
        double vowelsCount = new StatisticalAnalysis(iterableText.charIterator())
                .countOf("a", "e", "i", "o", "u");
        double allCharacterQuantity = new StatisticalAnalysis(iterableText.charIterator()).size();
        rootView.displayVowelsPercentage((vowelsCount / allCharacterQuantity) * 100);
    }

    private void showAToERatio(IterableText iterableText) {
        double aCount = new StatisticalAnalysis(iterableText.charIterator()).countOf("a");
        double eCount = new StatisticalAnalysis(iterableText.charIterator()).countOf("e");
        rootView.displayAToERatio(aCount / eCount);
    }

    private void showPercentageOfAllLetters(IterableText iterableText) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        double allCharacterQuantity = new StatisticalAnalysis(iterableText.charIterator()).size();
        Map<String, Double> map = new HashMap<>();
        for (char ch : alphabet) {
            int characterCount = new StatisticalAnalysis(iterableText.charIterator()).countOf(Character.toString(ch));
            map.put(Character.toString(ch), (characterCount / allCharacterQuantity) * 100);
        }
        rootView.displayPercentageOfAllLetters(map);
    }

    private void showBenchmark() {
        long estimatedTime = System.nanoTime() - startingTime;
        double time = (double)estimatedTime / 1000000000.0;
        rootView.displayBenchmarkTime(time);
    }
}