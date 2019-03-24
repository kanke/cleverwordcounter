import org.apache.commons.io.FilenameUtils;

public class WordFileFactory {

    private static final String CSV = "csv";
    private static final String TXT = "txt";
    private static final String JSON = "json";

    public WordFile getWordCount(String fileName) {

        String fileExtension = FilenameUtils.getExtension(fileName);
        switch (fileExtension) {
            case CSV:
                return new CSVWordFile(fileName);
            case TXT:
                return new TxtWordFile(fileName);
            case JSON:
                return new JSONWordFile(fileName);
        }

        return null;
    }
}
