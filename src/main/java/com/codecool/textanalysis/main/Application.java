package com.codecool.textanalysis.main;

import com.codecool.textanalysis.controllers.RootController;
import com.codecool.textanalysis.views.RootView;

import java.io.PrintStream;

public class Application {

    public static void main(String... args) {

        PrintStream printStream = new PrintStream(System.out);
        RootView rootView = new RootView(printStream);
        RootController rootController = new RootController(args, rootView);
        rootController.start();
    }
}