package org.kanke.services;

import org.apache.commons.io.FilenameUtils;
import org.kanke.services.impl.CSVWordFile;
import org.kanke.services.impl.JSONWordFile;
import org.kanke.services.impl.TxtWordFile;

public class WordFileFactory {

    private static final String CSV = "csv";
    private static final String TXT = "txt";
    private static final String JSON = "json";

    public WordFile getWordFile(String fileName) throws IllegalArgumentException {

        String fileExtension = FilenameUtils.getExtension(fileName);
        switch (fileExtension) {
            case CSV:
                return new CSVWordFile(fileName);
            case TXT:
                return new TxtWordFile(fileName);
            case JSON:
                return new JSONWordFile(fileName);
        }

        throw new IllegalArgumentException("Unsupported file type -_-");
    }
}
