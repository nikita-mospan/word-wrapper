package org.example;

public class Wrapper {
    public static String wrap(String inputString, int maxLineLength) {

        if (maxLineLength <= 0) {
            throw new IllegalArgumentException("maxLineLength argument must be greater than 0, but got " + maxLineLength);
        }

        if (inputString == null) {
            throw new IllegalArgumentException("inputString argument must be not null");
        }

        if (inputString.length() <= maxLineLength) {
            return inputString;
        }

        final char SPACE = ' ';
        final char NEWLINE = '\n';

        StringBuilder accumulator = new StringBuilder();
        accumulator.append(inputString);
        int processedPosition = 0;
        int insertedNewlines = 0;
        while (processedPosition + maxLineLength < inputString.length()) {
            int indexOfSpace = inputString.lastIndexOf(SPACE, processedPosition + maxLineLength);
            if (indexOfSpace > processedPosition - 1) {
                accumulator.setCharAt(insertedNewlines + indexOfSpace, NEWLINE);
                processedPosition = indexOfSpace + 1;
            } else {
                accumulator.insert(insertedNewlines + processedPosition + maxLineLength, NEWLINE);
                processedPosition += maxLineLength;
                insertedNewlines++;
            }
        }
        return accumulator.toString();
    }
}
