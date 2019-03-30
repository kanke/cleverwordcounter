package org.kanke;

import org.apache.commons.io.FilenameUtils;

public class WordFileFactory {

    private static final String CSV = "csv";
    private static final String TXT = "txt";

    WordFile getWordFile(String fileName) throws IllegalArgumentException {

        String fileExtension = FilenameUtils.getExtension(fileName);
        switch (fileExtension) {
            case CSV:
                return new CSVWordFile(fileName);
            case TXT:
                return new TxtWordFile(fileName);
        }

        throw new IllegalArgumentException("Unsupported file type -_-");
    }
}
