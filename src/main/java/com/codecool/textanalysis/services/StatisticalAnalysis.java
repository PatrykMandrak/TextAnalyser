package com.codecool.textanalysis.services;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class StatisticalAnalysis {

    private Iterator<String> iterator;

    public StatisticalAnalysis(Iterator<String> iterator)  {
        this.iterator = iterator;
    }

    public int countOf(String... elements) {
        int occurrences = 0;
        while (iterator.hasNext()) {
            if (Arrays.asList(elements).contains(iterator.next())) {
                occurrences++;
            }
        }
        return occurrences;
    }

    public int dictionarySize() {
        Set<String> elements = new HashSet<>();
        while (iterator.hasNext()) {
            elements.add(iterator.next());
        }
        return elements.size();
    }

    public int size() {
        int totalNumber = 0;
        while (iterator.hasNext()) {
            iterator.next();
            totalNumber++;
        }
        return totalNumber;
    }

    public Set<String> occurMoreThan(Integer n) {
        Map<String, Integer> map = new HashMap<>();

        while (iterator.hasNext()) {
            String element = iterator.next();
            map.put(element, !map.containsKey(element) ? 1 : map.get(element) + 1);
        }

        return map.entrySet().stream()
                .filter(a -> a.getValue() > n)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue))
                .keySet();
    }
}