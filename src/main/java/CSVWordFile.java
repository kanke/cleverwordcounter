import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CSVWordFile implements WordFile {

    private String fileName;

    public CSVWordFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Integer> countWords() {
        Map<String, Integer> wordMap = new HashMap<>();
        PrintStream errorPrintStream = null;

        try {
            errorPrintStream = new PrintStream("csvErrorLog.txt");
            CSVReader reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;

            if (reader.readNext() == null) {
                System.out.println("\nSorry! this csv file is empty O_O \n");
            }
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    Arrays.stream(nextLine).forEach(csvLine -> {
                        String[] words = csvLine.split(",");
                        for (int i = 0; i < words.length; i++) {
                            if (wordMap.containsKey(words[i])) {
                                wordMap.put(words[i], wordMap.get(words[i]) + 1);
                            } else {
                                wordMap.put(words[i], 1);
                            }
                        }
                    });
                }
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("\nCan't find csv file, please enter a valid path and try again -_-\n");

        } catch (IOException e) {
            System.setErr(errorPrintStream);
            e.printStackTrace();
            System.out.println("\nProblems reading from CSV file, the error has been logged. please try again later -_-\n");
        }

        return wordMap;
    }
}
