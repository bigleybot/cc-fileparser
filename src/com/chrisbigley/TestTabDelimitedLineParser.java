package com.chrisbigley;

import com.chrisbigley.factory.LineParserFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Christopher Bigley
 * Circa: 4/11/18
 */

public final class TestTabDelimitedLineParser {

    /**
     * Main method used exclusively for testing in this code exercise
     *
     * @param args
     */
    public static void main(String[] args) {
        runTests();
    }

    /**
     * Method that runs all tests
     */
    private static void runTests() {
        System.out.println("RUNNING TEST SCENARIOS\n");
        System.out.println("No issues test passed: " + testGetNextLineTokens_noIssues() + "\n\n");
        System.out.println("Empty file test passed: " + testGetNextLineTokens_emptyFile() + "\n\n");
        System.out.println("File not found test passed: " + testGetNextLineTokens_fileNotFound() + "\n\n");
        System.out.println("Unknown file extension test passed: " + testGetNextLineTokens_unknownFileExtension() + "\n\n");
    }

    /**
     * Tests method getNextLineTokens() when the file provided exists but is empty
     *
     * @return true if Exception is thrown, false if no exception is thrown
     */
    private static boolean testGetNextLineTokens_emptyFile() {
        System.out.println("Testing getNextLineTokens() empty file scenario:");
        //create a new File object from our test file
        File file = new File("test_file_empty.tab");

        //test whether the code throws an expected exception
        if (!isFileParseSuccessful(file)) {
            return true;
        }
        return false;
    }

    /**
     * Tests method getNextLineTokens() when the file provided has an unknown file extension
     *
     * @return true if Exception is thrown, false if no exception is thrown
     */
    private static boolean testGetNextLineTokens_unknownFileExtension() {
        System.out.println("Testing getNextLineTokens() unknown file extension scenario:");
        File file = new File("test_file.csv");

        //test whether the code throws an expected exception
        if (!isFileParseSuccessful(file)) {
            return true;
        }
        return false;
    }

    /**
     * Tests method getNextLineTokens() when the file provided cannot be found
     *
     * @return true if Exception is thrown, false if no exception is thrown
     */
    private static boolean testGetNextLineTokens_fileNotFound() {
        System.out.println("Testing getNextLineTokens() file not found scenario:");
        File file = new File("doesnt_exist.tab");

        //test whether the code throws an expected exception
        if (!isFileParseSuccessful(file)) {
            return true;
        }
        return false;
    }

    /**
     * Tests method getNextLineTokens() when the file provided exists, has a known file extension and contains multiple
     * lines of text
     *
     * @return true if all data is parsed correctly and no exception is thrown, false if exception is thrown or data is
     * not parsed correctly
     */
    private static boolean testGetNextLineTokens_noIssues() {
        System.out.println("Testing getNextLineTokens() no issues scenario:");
        File file = new File("test_file.tab");
        ILineParser lineParser;
        LineParserFactory lineParserFactory;
        List<String> lineOneTestStrings = new ArrayList<>();
        List<String> lineTwoTestStrings = new ArrayList<>();

        //set expected String values
        lineOneTestStrings.add("This");
        lineOneTestStrings.add("is");
        lineOneTestStrings.add("a");
        lineOneTestStrings.add("test");
        lineTwoTestStrings.add("Red");
        lineTwoTestStrings.add("green");
        lineTwoTestStrings.add("blue");

        //test whether the code throws an exception
        if (!isFileParseSuccessful(file)) {
            return false;
        }

        //initialize line parser factory
        lineParserFactory = new LineParserFactory();

        //test whether the values returned from getNextLineTokens() match the expected values
        try {
            lineParser = lineParserFactory.getLineParser(file);
            if (lineParser.getNextLineTokens().equals(lineOneTestStrings)
                    && lineParser.getNextLineTokens().equals(lineTwoTestStrings)
                    && lineParser.getNextLineTokens() == null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Method used to attempt a file parse
     *
     * @param file
     * @return true if parse completed successfully, false if an exception is thrown
     */
    private static boolean isFileParseSuccessful(File file) {
        ILineParser lineParser;
        LineParserFactory lineParserFactory;

        //set our line parser factory
        lineParserFactory = new LineParserFactory();

        //test getNextLineTokens() with a loop and output the result to console
        try {
            lineParser = lineParserFactory.getLineParser(file);
            //for loop provides enough calls to exceed the number of lines in the test file to show null is returned
            for (int i = 0; i < 3; i++) {
                System.out.println(lineParser.getNextLineTokens());
            }
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

}
