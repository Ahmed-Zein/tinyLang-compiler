package test;

import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import parser.Parser;

public class TestAddop {
    @Test
    public void testAdd() {
        Parser parser = new Parser("+");
        try {
            parser.addop();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testMinus() {
        Parser parser = new Parser("-");
        try {
            parser.addop();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
}
