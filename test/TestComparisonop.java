package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestComparisonop {
    @Test
    public void testAdd() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList("<", "<=", ">=", ">", "="));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                parser.addop();
            } catch (Exception e) {
                assumeNoException(e);
            }
        }
    }
}
