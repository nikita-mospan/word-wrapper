package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WrapperTest {

    @Test(expected = IllegalArgumentException.class)
    public void maxLineLengthLessThanZero() {
        Wrapper.wrap("aaa", -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxLineLengthIsZero() {
        Wrapper.wrap("aaa", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void inputStringIsNull() {
        Wrapper.wrap(null, 3);
    }

    @Test
    public void inputStringIsEmpty() {
        final String inputString  = "";
        Assert.assertEquals(inputString, Wrapper.wrap(inputString, 3));
    }

    @Test
    public void inputStringLengthLessThanMaxLineLength() {
        final String inputString  = "Hello World";
        Assert.assertEquals(inputString, Wrapper.wrap(inputString, 15));
    }

    @Test
    public void casualTest() {
        final String inputString  = "Hello World";
        final String expected = "Hello\nWorld";
        Assert.assertEquals(expected, Wrapper.wrap(inputString, 5));
    }

    @Test
    public void longLine() {
        final int inputStringLength = 1_000_000;
        final String inputString = new String(new char[inputStringLength]).replace('\0', 'a');
        final int maxLineLength = 50;
        final String singleLine = new String(new char[maxLineLength]).replace('\0', 'a');
        final String expected = IntStream.rangeClosed(1, inputStringLength / maxLineLength)
                .mapToObj(i -> singleLine).collect(Collectors.joining("\n"));
        Assert.assertEquals(expected, Wrapper.wrap(inputString, maxLineLength));
    }

}
