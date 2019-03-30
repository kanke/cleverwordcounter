package org.kanke;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@Slf4j
public class WordCounter {

    public static void main(String[] args) {

        System.out.println("\n\n***** ^_^ Welcome to Clever org.kanke.WordFile Counter ^_^ ******\n");

        System.out.println("Kindly enter a wordFile path below to continue #^_^# :");

        WordFileFactory wordFileFactory = new WordFileFactory();
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        WordFile wordFile = wordFileFactory.getWordFile(fileName);

        printProgramOutput(wordFile);

    }

    static void printProgramOutput(WordFile wordFile) {
        try {
            wordFile.countWords().entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEachOrdered(wordEntry -> System.out.println("\n" + wordEntry.getKey() + ": " + wordEntry.getValue()));
        } catch (IOException ex) {

            //add logger to file
            log.error(String.valueOf(ex));
            System.out.println("\nCan't find file, please enter a valid path and try again -_-\n");
        } catch (IllegalArgumentException ex) {

            //add logger to file
            log.error(String.valueOf(ex));
            System.out.println("Unsupported file type -_- ");
        }
    }
}

