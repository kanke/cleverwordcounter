package org.kanke;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class TxtWordFile implements WordFile {

    private String fileName;

    TxtWordFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Long> countWords() throws IOException {

        String contents = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);

        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        Map<String, Long> wordToFrequencyCount = new HashMap<>();

        if (words.size() == 0) {
            System.out.println("\nSorry! this txt file is empty O_O \n");
        } else {
            wordToFrequencyCount = words.stream().collect(groupingBy(group -> group, counting()));
        }

        return wordToFrequencyCount;
    }
}
