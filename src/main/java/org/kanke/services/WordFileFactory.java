package org.kanke.services;

import org.apache.commons.io.FilenameUtils;
import org.kanke.services.impl.CSVWordFileImpl;
import org.kanke.services.impl.WordFileImpl;

public class WordFileFactory {

    private static final String CSV = "csv";
    private static final String TXT = "txt";
    private static final String JSON = "json";
    private static final String XML = "xml";

    public WordFile getWordFile(String fileName) throws IllegalArgumentException {

        String fileExtension = FilenameUtils.getExtension(fileName);
        switch (fileExtension) {
            case CSV:
                return new CSVWordFileImpl(fileName);
           case JSON:
            case XML:
            case TXT:
                return new WordFileImpl(fileName);
        }

        throw new IllegalArgumentException();
    }
}
