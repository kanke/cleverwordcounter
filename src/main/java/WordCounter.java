import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {

        System.out.println("\n\n***** ^_^ Welcome to Clever WordFile Counter ^_^ ******\n");

        System.out.println("Kindly enter a wordFile path below to continue #^_^# :");

        WordFileFactory wordFileFactory = new WordFileFactory();
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        WordFile wordFile = wordFileFactory.getWordCount(fileName);

        if (wordFile != null) {
            printWordCount(wordFile.countWords());
        } else {
            System.out.println("Unsupported file type -_- ");
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

