package test;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import parser.Parser;

public class TestFactor {

    // testing factor, ()
    @Test
    public void testfactor() {
        Parser parser = new Parser("()");
        assertEquals(true, parser.factor());
    }
    @Test
    public void testfactor2(){
        Parser parser = new Parser(")()");
        assertEquals(false, parser.factor());
    }

    @Test
    public void testfactor3(){
        Parser parser = new Parser("123");
        assertEquals(true, parser.factor());
    }

}