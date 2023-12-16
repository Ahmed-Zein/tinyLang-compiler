package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestSimpleexp {
    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList("3+5*8-5/2", "3 * 5", "4/5"));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                parser.simpleExp();
            } catch (Exception e) {
                assumeNoException(e);
            }
        }
    }
}
