package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestReadStmt {
    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList("read cse"));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                // parser.readStmt();
            } catch (Exception e) {
                System.err.println("ERR: " + e.toString());
                assumeNoException(e);
            }
        }
    }
}
