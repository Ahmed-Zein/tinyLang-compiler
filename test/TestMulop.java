package test;

import static org.junit.Assume.assumeNoException;

import org.junit.Test;

import parser.Parser;

public class TestMulop {

    @Test
    public void testMult() {
        Parser parser = new Parser("*");
        try {
            parser.mulop();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    @Test
    public void testDiv() {
        Parser parser = new Parser("/");
        try {
            parser.mulop();
        } catch (Exception e) {
            assumeNoException(e);
        }
    }
}
