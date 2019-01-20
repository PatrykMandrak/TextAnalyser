package com.codecool.textanalysis.controllers;

import com.codecool.textanalysis.services.FileContent;
import com.codecool.textanalysis.services.IterableText;
import com.codecool.textanalysis.views.RootView;
import com.codecool.textanalysis.services.StatisticalAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
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
}