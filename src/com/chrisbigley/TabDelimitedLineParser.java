package com.chrisbigley;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Christopher Bigley
 * Circa: 4/11/18
 */

public class TabDelimitedLineParser implements ILineParser {

    //path of the file to be parsed
    private String filePath;
    //current index used to track which line of the file is next to be parsed
    private int currentLineIndex;

    /**
     * Main constructor that initializes required values
     *
     * @param filePath absolute path of the file that the class will use to parse
     */
    public TabDelimitedLineParser(String filePath) {
        //set the current line index to 0
        this.currentLineIndex = 0;
        this.filePath = filePath;
    }

    /**
     * Method returns the individual String values from a tab delimited string
     *
     * @return list of Strings previously separated by tab delimiters
     * @throws IOException when an IO Exception occurs during file reading
     */
    @Override
    public List<String> getNextLineTokens() throws IOException {
        String line;
        List<String> tokenList;
        List<String> stringList;
        try {
            Stream<String> lines = Files.lines(Paths.get(this.filePath));
            //Collect all lines from above into a list of strings
            stringList = lines
                    .collect(Collectors.toList());
            lines.close();
            if (stringList.isEmpty()) {
                throw new RuntimeException("The file at " + this.filePath + " is empty...");
            }
        } catch (IOException ioe) {
            //I catch the standard IOException and throw a new one with a custom message
            throw new IOException("The file at " + this.filePath + " cannot be found...");
        }

        if (this.currentLineIndex < stringList.size()) {
            line = stringList.get(currentLineIndex);
            tokenList = Stream.of(line.split("\t"))
                    .map(elem -> new String(elem))
                    .collect(Collectors.toList());
            currentLineIndex++;
            return tokenList;
        } else {
            return null;
        }
    }

    /**
     * This method resets the current index of the class to 0
     *
     */
    public void resetCurrentIndex() {
        this.currentLineIndex = 0;
    }
}
