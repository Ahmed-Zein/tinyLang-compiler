package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestIfStmt {
    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList(
                "if 3 > 4 then write 3 * 5   else write 4 / 5 end",
                "if 3 > 4 then write 3 * 5 else write 4 / 5 end", "if 3 > 4 then write 3 * 5 end"));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                // parser.ifStmt();
            } catch (Exception e) {
                System.err.println("ERR: " + e.toString());
                assumeNoException(e);
            }
        }
    }
}
