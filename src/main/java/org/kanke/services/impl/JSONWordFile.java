package org.kanke.services.impl;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.kanke.services.WordFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class JSONWordFile implements WordFile {

    private String fileName;

    public JSONWordFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Long> countWords() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader(fileName);
        Map<String, Long> wordToFrequencyCount = new HashMap<>();

        String jsonString = jsonParser.parse(reader).toString();

        //Strip special characters, empty spaces and get words
        List<String> words = Arrays.asList(jsonString.split("[\\P{L}]+")).stream()
                .filter(word -> !word.equals(""))
                .collect(Collectors.toList());

        if (words.isEmpty()) {
            System.out.println("\nSorry! this txt file is empty O_O \n");
        } else {
            wordToFrequencyCount = words.stream().collect(groupingBy(group -> group, counting()));
        }

        return wordToFrequencyCount;
    }

}
