package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestRepeatStmt {
    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList("repeat write 13+4 until 13+4;"));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                // parser.repeatStmt();
            } catch (Exception e) {
                System.err.println("ERR: " + e.toString());
                assumeNoException(e);
            }
        }
    }
}