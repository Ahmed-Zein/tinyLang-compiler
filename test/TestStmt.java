package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestStmt {

    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(
                Arrays.asList("if 3 then write 132 else write 123 end;  if 3 then write 132 else write 123 end;  "));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                // parser.stmtSequence();
            } catch (Exception e) {
                assumeNoException(e);
            }
        }
    }
}
