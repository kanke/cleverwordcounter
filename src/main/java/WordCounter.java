
import com.opencsv.CSVReader;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class WordCounter {

    private static final String CSV = "csv";
    private static final String TXT = "txt";
    private static final String JSON = "json";

    public static void main(String[] args) {
        System.out.println("\n\n***** ^_^ Welcome to Clever Word Counter ^_^ ******\n");

        System.out.println("Kindly enter a file path below to continue #^_^# :");

        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        switch (FilenameUtils.getExtension(fileName)) {
            case CSV:
                printWordCount(CSVWordCounter.csvWordCounter(fileName));
                break;
            case TXT:
                printWordCount(TxtWordCounter.txtWordCounter(fileName));
                break;
            case JSON:
                printWordCount(TxtWordCounter.txtWordCounter(fileName));
                break;
            default:
                System.out.println("Unsupported file type -_- ");
                break;
        }

    }


    private static void printWordCount(Map<String, Integer> wordMap) {
        if (!wordMap.isEmpty()) {
            wordMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEachOrdered(x -> System.out.println("\n" + x.getKey() + ": " + x.getValue()));
        }
    }
}

