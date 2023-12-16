package test;

import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import parser.Parser;

public class TestAssign {
    @Test
    public void test() {
        ArrayList<String> allowedList = new ArrayList<>(Arrays.asList("cse:=3 * 5", "asu := (129*(12+11*2))*4/5"));
        for (String s : allowedList) {
            Parser parser = new Parser(s);
            try {
                parser.assignStmt();
            } catch (Exception e) {
                assumeNoException(e);
            }
        }
    }
}
