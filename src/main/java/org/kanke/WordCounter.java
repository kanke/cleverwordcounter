package org.kanke;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kanke.services.WordFile;
import org.kanke.services.WordFileFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class WordCounter {

    private static final Logger logger = LogManager.getLogger(WordCounter.class);

    public static void main(String[] args) {

        System.out.println("\n\n***** ^_^ Welcome to Clever Word Counter ^_^ ******\n");
        System.out.println("Kindly enter a file path below to continue, supported files are txt, csv, xml= and json #^_^# :");

        WordFileFactory wordFileFactory = new WordFileFactory();
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        try {
            WordFile wordFile = wordFileFactory.getWordFile(fileName);
            String userOutputString = getProgramOutput(wordFile);
            System.out.println(userOutputString);
        } catch (IllegalArgumentException ex) {
            System.out.println("\nUnsupported file type -_- \n");
            logger.error(ex);
        }


    }

    static String getProgramOutput(WordFile wordFile) {
        String userOutput;
        try {
            userOutput = wordFile.countWords().entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .map(wordEntry -> "\n" + wordEntry.getKey() + ": " + wordEntry.getValue())
                    .collect(Collectors.joining("\n"));
        } catch (NoSuchFileException ex) {
            userOutput = "\nDoes this file exist? please enter a valid path and try again -_-\n";

            //log error messages to file
            logger.error(ex);
        } catch (FileNotFoundException ex) {
            userOutput = "\nCan't find file, please enter a valid path and try again -_-\n";

            //log error messages to file
            logger.error(ex);
        } catch (IOException ex) {
            userOutput = "\nProblems reading from file, the error has been logged. please check file and try again later -_-\n";

            //log error messages to file
            logger.error(ex);
        }
        return userOutput;
    }
}

