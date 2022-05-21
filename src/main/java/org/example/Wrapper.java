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
        int processedIndex = 0;
        while (processedIndex + maxLineLength < inputString.length()) {
            int indexOfSpace = inputString.lastIndexOf(SPACE, processedIndex + maxLineLength);
            if (indexOfSpace >= processedIndex) {
                accumulator.append(inputString, processedIndex, indexOfSpace);
                processedIndex = indexOfSpace + 1;
            } else {
                accumulator.append(inputString, processedIndex, processedIndex + maxLineLength);
                processedIndex += maxLineLength;
            }
            accumulator.append(NEWLINE);
        }
        accumulator.append(inputString, processedIndex, inputString.length());
        return accumulator.toString();
    }
}
