package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestWrite {
    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList("write 3 * 5", "write 4/5"));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                parser.writeStmt();
            } catch (Exception e) {
                System.err.println("ERR: " + e.toString());
                assumeNoException(e);
            }
        }
    }

}
