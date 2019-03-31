package org.kanke.services.impl;

import com.opencsv.CSVReader;
import org.kanke.services.WordFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVWordFileImpl implements WordFile {

    private String fileName;

    public CSVWordFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Long> countWords() throws IOException {
        Map<String, Long> wordMap = new HashMap<>();

        CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;

            if (reader.readNext() == null) {
                wordMap.put("Sorry! this file contains no valid words O_O", 0l);
            }
            while ((nextLine = reader.readNext()) != null) {
                    for (String csvLine : nextLine) {
                        String[] words = csvLine.split(",");
                        for (String word : words) {
                            if (wordMap.containsKey(word)) {
                                wordMap.put(word, wordMap.get(word) + 1);
                            } else {
                                wordMap.put(word, 1l);
                            }
                        }
                    }

            }

        return wordMap;
    }
}
