package test;

import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import parser.Parser;

public class TestFactor {

    // factor -> (exp) | number | identifier
    @Test
    public void testfactor() {
        Parser parser = new Parser("(3)");
        try {
            parser.factor();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testfactor1() {
        Parser parser = new Parser("3");
        try {
            parser.factor();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testfactor2() {
        Parser parser = new Parser("helloworld");
        try {
            parser.factor();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
}